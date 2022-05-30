package com.example.googlemapspringboot.service.impl;

import com.example.googlemapspringboot.dto.PlacesDto;
import com.example.googlemapspringboot.entity.Coordinates;
import com.example.googlemapspringboot.entity.Places;
import com.example.googlemapspringboot.repository.CoordinatesRepository;
import com.example.googlemapspringboot.service.CoordinatesService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoordinatesServiceImpl implements CoordinatesService {


    @Autowired
    CoordinatesRepository coordinatesRepository;

    @Override
    public void save(BigDecimal lat, BigDecimal lng, int radius, List<PlacesDto> placesList) {

        Coordinates coordinates = new Coordinates();
        coordinates.setLat(new BigDecimal(String.valueOf(lat)));
        coordinates.setLng(new BigDecimal(String.valueOf(lng)));
        coordinates.setRadius(radius);
        Places places ;
        List<Places> list = new ArrayList<>();

        for (int i = 0;i<placesList.size();i++){

            places = new Places(placesList.get(i).getId(),placesList.get(i).getName(),placesList.get(i).getVicinity(),new BigDecimal(String.valueOf(placesList.get(i).getLat())),
                    new BigDecimal(String.valueOf(placesList.get(i).getLng())), coordinates);

            list.add(places);
        }
        coordinates.setPlaces(list);

        coordinatesRepository.save(coordinates);
    }

    @Override
    public Coordinates getCoordinatBy(BigDecimal lat, BigDecimal lng, int radius) {
        return coordinatesRepository.findByLatAndLngAndRadius(lat, lng, radius);
    }

    @Override
    public List<PlacesDto> nearbySearch(BigDecimal getLat, BigDecimal getLng, int getRadius) {
        List<PlacesDto> placesList = new ArrayList<>();
        PlacesDto place;
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+getLat+"%2C"+getLng+"&radius="+getRadius+"&key=AIzaSyC5__ph5uSMinywCZ2HQELkQKN7XexMzEs")
                .method("GET", null)
                .build();
        try {
            Response response = client.newCall(request).execute();

            JSONObject jsonObj = new JSONObject(response.body().string());
            JSONArray arrObj = jsonObj.getJSONArray("results");

            for (int i = 0; i < arrObj .length(); i++) {

                String place_id = arrObj.getJSONObject(i).getString("place_id");
                String name = arrObj.getJSONObject(i).getString("name");
                String vicinity = arrObj.getJSONObject(i).getString("vicinity");
                BigDecimal lat =arrObj.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getBigDecimal("lat");
                BigDecimal lng =arrObj.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getBigDecimal("lng");
                place = new PlacesDto(place_id,name,vicinity,lat,lng);
                placesList.add(place);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return placesList;
    }

}

package com.example.googlemapspringboot.controller;

import com.example.googlemapspringboot.dto.PlacesDto;
import com.example.googlemapspringboot.dto.ServiceResponse;
import com.example.googlemapspringboot.service.CoordinatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CoordinatesController {

    ServiceResponse<List<PlacesDto>> response =null;
    List<PlacesDto> placesList = new ArrayList<>();

    @Autowired
    CoordinatesService service;

    @GetMapping("/nearby-search/{lat}/{lng}/{rad}")
    public ResponseEntity<Object> nearbySearch(@PathVariable("lat") BigDecimal lat, @PathVariable("lng") BigDecimal lng, @PathVariable("rad") int rad){

        try {
            placesList = service.nearbySearch(lat, lng, rad);
            response = new ServiceResponse<>("success", placesList);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new ServiceResponse<>("error", null);
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        }
    }
}

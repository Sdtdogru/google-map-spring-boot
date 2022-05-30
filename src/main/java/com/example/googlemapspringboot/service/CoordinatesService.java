package com.example.googlemapspringboot.service;


import com.example.googlemapspringboot.dto.PlacesDto;
import com.example.googlemapspringboot.entity.Coordinates;

import java.math.BigDecimal;
import java.util.List;

public interface CoordinatesService {

    List<PlacesDto> nearbySearch(BigDecimal getLat, BigDecimal getLng, int getRadius);
    void save(BigDecimal lat, BigDecimal lng, int radius, List<PlacesDto> placesList);
    Coordinates getCoordinatBy(BigDecimal lat, BigDecimal lng, int ladius);
}

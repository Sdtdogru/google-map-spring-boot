package com.example.googlemapspringboot.service;


import com.example.googlemapspringboot.dto.PlacesDto;

import java.math.BigDecimal;
import java.util.List;

public interface CoordinatesService {

    List<PlacesDto> nearbySearch(BigDecimal getLat, BigDecimal getLng, int getRadius);

}

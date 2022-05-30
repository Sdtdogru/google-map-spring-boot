package com.example.googlemapspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
public class PlacesDto {
    private String id;
    private String name;
    private String vicinity;
    private BigDecimal lat;
    private BigDecimal lng;
}

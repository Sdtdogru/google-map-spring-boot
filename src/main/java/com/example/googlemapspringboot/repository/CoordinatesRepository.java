package com.example.googlemapspringboot.repository;

import com.example.googlemapspringboot.entity.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CoordinatesRepository extends JpaRepository<Coordinates,Long> {
    Coordinates findByLatAndLngAndRadius(BigDecimal lat, BigDecimal lng, int radius);
}

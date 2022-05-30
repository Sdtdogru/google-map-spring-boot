package com.example.googlemapspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( precision = 16, scale = 7)
    private BigDecimal lat;
    @Column( precision = 16, scale = 7)
    private BigDecimal lng;
    private int radius;

    @OneToMany(mappedBy="coordinates",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Places> places = new ArrayList<>();
}

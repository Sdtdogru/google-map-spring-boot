package com.example.googlemapspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Places {
    @Id
    private String id;
    private String name;
    private String vicinity;
    @Column( precision = 16, scale = 7)
    private BigDecimal lat;
    @Column( precision = 16, scale = 7)
    private BigDecimal lng;

    @ManyToOne
    @JoinColumn(name="coordinates", nullable=false)
    private Coordinates nearby;


}

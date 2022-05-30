package com.example.googlemapspringboot.repository;



import com.example.googlemapspringboot.entity.Places;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Places,String> {
}

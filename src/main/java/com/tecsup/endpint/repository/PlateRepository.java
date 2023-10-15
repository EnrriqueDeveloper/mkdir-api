package com.tecsup.endpint.repository;

import com.tecsup.endpint.model.Plate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlateRepository extends JpaRepository<Plate, Integer> {
    List<Plate> findByName(String name);
}
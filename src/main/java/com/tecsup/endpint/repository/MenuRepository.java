package com.tecsup.endpint.repository;

import com.tecsup.endpint.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findByName(String name);
}


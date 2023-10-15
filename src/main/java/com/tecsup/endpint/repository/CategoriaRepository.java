package com.tecsup.endpint.repository;

import com.tecsup.endpint.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    // Por ejemplo, buscar categorías por nombre
    List<Categoria> findByName(String name);
}
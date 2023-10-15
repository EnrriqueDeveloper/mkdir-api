package com.tecsup.endpint.repository;

import com.tecsup.endpint.model.Menu;
import com.tecsup.endpint.model.MenuPlate;
import com.tecsup.endpint.model.Plate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuPlateRepository extends JpaRepository<MenuPlate, Integer> {

    // Por ejemplo, buscar relaciones MenuPlate por menú o plato específico
    List<MenuPlate> findByMenu(Menu menu);
    List<MenuPlate> findByPlate(Plate plate);
}
package com.tecsup.endpint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tecsup.endpint.model.MenuPlate;
import com.tecsup.endpint.repository.MenuPlateRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api_mkdir/menuplates")
public class MenuPlateController {

    @Autowired
    private MenuPlateRepository menuPlateRepository;

    // Endpoint para crear una nueva relación MenuPlate
    @PostMapping("/create")
    public ResponseEntity<String> createMenuPlate(@RequestBody MenuPlate menuPlate) {
        menuPlateRepository.save(menuPlate);
        return ResponseEntity.status(HttpStatus.CREATED).body("MenuPlate created successfully");
    }

    // Endpoint para obtener una relación MenuPlate por su ID
    @GetMapping("/{id}")
    public ResponseEntity<MenuPlate> getMenuPlateById(@PathVariable Integer id) {
        Optional<MenuPlate> optionalMenuPlate = menuPlateRepository.findById(id);
        return optionalMenuPlate.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para obtener todas las relaciones MenuPlate
    @GetMapping("/all")
    public ResponseEntity<List<MenuPlate>> getAllMenuPlates() {
        List<MenuPlate> menuPlates = menuPlateRepository.findAll();
        return ResponseEntity.ok(menuPlates);
    }

    // Endpoint para eliminar una relación MenuPlate por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMenuPlate(@PathVariable Integer id) {
        Optional<MenuPlate> optionalMenuPlate = menuPlateRepository.findById(id);
        if (optionalMenuPlate.isPresent()) {
            menuPlateRepository.deleteById(id);
            return ResponseEntity.ok("MenuPlate deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MenuPlate not found");
    }
}
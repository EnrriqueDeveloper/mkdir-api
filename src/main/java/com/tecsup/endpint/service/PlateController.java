package com.tecsup.endpint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tecsup.endpint.model.Plate;
import com.tecsup.endpint.repository.PlateRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api_mkdir/plates")
public class PlateController {

    @Autowired
    private PlateRepository plateRepository;

    // Endpoint para crear un nuevo plato
    @PostMapping("/create")
    public ResponseEntity<String> createPlate(@RequestBody Plate plate) {
        plateRepository.save(plate);
        return ResponseEntity.status(HttpStatus.CREATED).body("Plate created successfully");
    }

    // Endpoint para obtener un plato por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Plate> getPlateById(@PathVariable Integer id) {
        Optional<Plate> optionalPlate = plateRepository.findById(id);
        return optionalPlate.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para obtener todos los platos
    @GetMapping("/all")
    public ResponseEntity<List<Plate>> getAllPlates() {
        List<Plate> plates = plateRepository.findAll();
        return ResponseEntity.ok(plates);
    }

    // Endpoint para actualizar un plato por su ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePlate(@PathVariable Integer id, @RequestBody Plate updatedPlate) {
        Optional<Plate> optionalPlate = plateRepository.findById(id);
        if (optionalPlate.isPresent()) {
            Plate plate = optionalPlate.get();
            // Actualiza los campos del plato con los valores del objeto actualizado
            plate.setName(updatedPlate.getName());
            plate.setDescription(updatedPlate.getDescription());
            plate.setPrice(updatedPlate.getPrice());
            plate.setCategory(updatedPlate.getCategory());
            plate.setRestaurant(updatedPlate.getRestaurant());
            plate.setMenu(updatedPlate.getMenu());
            plateRepository.save(plate);
            return ResponseEntity.ok("Plate updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plate not found");
    }

    // Endpoint para eliminar un plato por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlate(@PathVariable Integer id) {
        Optional<Plate> optionalPlate = plateRepository.findById(id);
        if (optionalPlate.isPresent()) {
            plateRepository.deleteById(id);
            return ResponseEntity.ok("Plate deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plate not found");
    }
}

package com.tecsup.endpint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tecsup.endpint.model.Restaurant;
import com.tecsup.endpint.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api_mkdir/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    // Endpoint para crear un nuevo restaurante
    @PostMapping("/create")
    public ResponseEntity<String> createRestaurant(@RequestBody Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body("Restaurant created successfully");
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }

    // Endpoint para obtener un restaurante por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Integer id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        return optionalRestaurant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para obtener todos los restaurantes
    @GetMapping("/all")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return ResponseEntity.ok(restaurants);
    }

    // Endpoint para actualizar un restaurante por su ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRestaurant(@PathVariable Integer id, @RequestBody Restaurant updatedRestaurant) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            // Actualiza los campos del restaurante con los valores del objeto actualizado
            restaurant.setName(updatedRestaurant.getName());
            restaurant.setAddress(updatedRestaurant.getAddress());
            restaurant.setLogoUrl(updatedRestaurant.getLogoUrl());
            restaurant.setOpen(updatedRestaurant.getOpen());
            restaurantRepository.save(restaurant);
            return ResponseEntity.ok("Restaurant updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found");
    }

    // Endpoint para eliminar un restaurante por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Integer id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isPresent()) {
            restaurantRepository.deleteById(id);
            return ResponseEntity.ok("Restaurant deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found");
    }


}

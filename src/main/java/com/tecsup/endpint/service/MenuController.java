package com.tecsup.endpint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tecsup.endpint.model.Menu;
import com.tecsup.endpint.repository.MenuRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api_mkdir/menus")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    // Endpoint para crear un nuevo menú
    @PostMapping("/create")
    public ResponseEntity<String> createMenu(@RequestBody Menu menu) {
        menuRepository.save(menu);
        return ResponseEntity.status(HttpStatus.CREATED).body("Menu created successfully");
    }

    // Endpoint para obtener un menú por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Integer id) {
        Optional<Menu> optionalMenu = menuRepository.findById(id);
        return optionalMenu.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para obtener todos los menús
    @GetMapping("/all")
    public ResponseEntity<List<Menu>> getAllMenus() {
        List<Menu> menus = menuRepository.findAll();
        return ResponseEntity.ok(menus);
    }

    // Endpoint para actualizar un menú por su ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMenu(@PathVariable Integer id, @RequestBody Menu updatedMenu) {
        Optional<Menu> optionalMenu = menuRepository.findById(id);
        if (optionalMenu.isPresent()) {
            Menu menu = optionalMenu.get();
            // Actualiza los campos del menú con los valores del objeto actualizado
            menu.setName(updatedMenu.getName());
            menu.setDescription(updatedMenu.getDescription());
            menu.setPublished(updatedMenu.isPublished());
            menu.setRestaurant(updatedMenu.getRestaurant());
            menuRepository.save(menu);
            return ResponseEntity.ok("Menu updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu not found");
    }

    // Endpoint para eliminar un menú por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable Integer id) {
        Optional<Menu> optionalMenu = menuRepository.findById(id);
        if (optionalMenu.isPresent()) {
            menuRepository.deleteById(id);
            return ResponseEntity.ok("Menu deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu not found");
    }
}
package com.tecsup.endpint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tecsup.endpint.model.Categoria;
import com.tecsup.endpint.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api_mkdir/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Endpoint para crear una nueva categoría
    @PostMapping("/create")
    public ResponseEntity<String> createCategoria(@RequestBody Categoria categoria) {
        categoriaRepository.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body("Categoria created successfully");
    }

    // Endpoint para obtener una categoría por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        return optionalCategoria.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para obtener todas las categorías
    @GetMapping("/all")
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return ResponseEntity.ok(categorias);
    }

    // Endpoint para actualizar una categoría por su ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCategoria(@PathVariable Integer id, @RequestBody Categoria updatedCategoria) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            // Actualiza los campos de la categoría con los valores del objeto actualizado
            categoria.setName(updatedCategoria.getName());
            categoria.setDescription(updatedCategoria.getDescription());
            categoriaRepository.save(categoria);
            return ResponseEntity.ok("Categoria updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria not found");
    }

    // Endpoint para eliminar una categoría por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategoria(@PathVariable Integer id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.ok("Categoria deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria not found");
    }
}
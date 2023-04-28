package fr.epsi.atelier_persistance.controllers;

import fr.epsi.atelier_persistance.entities.Categorie;
import fr.epsi.atelier_persistance.repositories.CategorieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    private final CategorieRepository categorieRepository;

    public CategorieController(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) {
        Optional<Categorie> categorie = categorieRepository.findById(id);
        if (categorie.isPresent()) {
            return ResponseEntity.ok(categorie.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
        Categorie createdCategorie = categorieRepository.save(categorie);
        return new ResponseEntity<>(createdCategorie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie updatedCategorie) {
        Optional<Categorie> existingCategorie = categorieRepository.findById(id);
        if (existingCategorie.isPresent()) {
            Categorie categorie = existingCategorie.get();
            categorie.setNom(updatedCategorie.getNom());
            categorieRepository.save(categorie);
            return ResponseEntity.ok(categorie);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        Optional<Categorie> existingCategorie = categorieRepository.findById(id);
        if (existingCategorie.isPresent()) {
            categorieRepository.delete(existingCategorie.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

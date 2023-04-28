package fr.epsi.atelier_persistance.controllers;

import fr.epsi.atelier_persistance.entities.Auteur;
import fr.epsi.atelier_persistance.repositories.AuteurRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auteurs")
public class AuteurController {

    private final AuteurRepository auteurRepository;

    public AuteurController(AuteurRepository auteurRepository) {
        this.auteurRepository = auteurRepository;
    }

    @GetMapping
    public List<Auteur> getAllAuteurs() {
        return auteurRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auteur> getAuteurById(@PathVariable Long id) {
        Optional<Auteur> auteur = auteurRepository.findById(id);
        if (auteur.isPresent()) {
            return ResponseEntity.ok(auteur.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Auteur> createAuteur(@RequestBody Auteur auteur) {
        Auteur createdAuteur = auteurRepository.save(auteur);
        return new ResponseEntity<>(createdAuteur, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auteur> updateAuteur(@PathVariable Long id, @RequestBody Auteur updatedAuteur) {
        Optional<Auteur> existingAuteur = auteurRepository.findById(id);
        if (existingAuteur.isPresent()) {
            Auteur auteur = existingAuteur.get();
            auteur.setNom(updatedAuteur.getNom());
            auteur.setPrenom(updatedAuteur.getPrenom());
            auteurRepository.save(auteur);
            return ResponseEntity.ok(auteur);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuteur(@PathVariable Long id) {
        Optional<Auteur> existingAuteur = auteurRepository.findById(id);
        if (existingAuteur.isPresent()) {
            auteurRepository.delete(existingAuteur.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

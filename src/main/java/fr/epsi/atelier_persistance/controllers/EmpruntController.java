package fr.epsi.atelier_persistance.controllers;

import fr.epsi.atelier_persistance.entities.Emprunt;
import fr.epsi.atelier_persistance.repositories.EmpruntRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {

    private final EmpruntRepository empruntRepository;

    public EmpruntController(EmpruntRepository empruntRepository) {
        this.empruntRepository = empruntRepository;
    }

    @GetMapping
    public List<Emprunt> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprunt> getEmpruntById(@PathVariable Long id) {
        Optional<Emprunt> emprunt = empruntRepository.findById(id);
        if (emprunt.isPresent()) {
            return ResponseEntity.ok(emprunt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Emprunt> createEmprunt(@RequestBody Emprunt emprunt) {
        Emprunt createdEmprunt = empruntRepository.save(emprunt);
        return new ResponseEntity<>(createdEmprunt, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprunt> updateEmprunt(@PathVariable Long id, @RequestBody Emprunt updatedEmprunt) {
        Optional<Emprunt> existingEmprunt = empruntRepository.findById(id);
        if (existingEmprunt.isPresent()) {
            Emprunt emprunt = existingEmprunt.get();
            emprunt.setDateEmprunt(updatedEmprunt.getDateEmprunt());
            emprunt.setDateFinPrevue(updatedEmprunt.getDateFinPrevue());
            emprunt.setDateRetour(updatedEmprunt.getDateRetour());
            empruntRepository.save(emprunt);
            return ResponseEntity.ok(emprunt);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmprunt(@PathVariable Long id) {
        Optional<Emprunt> existingEmprunt = empruntRepository.findById(id);
        if (existingEmprunt.isPresent()) {
            empruntRepository.delete(existingEmprunt.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

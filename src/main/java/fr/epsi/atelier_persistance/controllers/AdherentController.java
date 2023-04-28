package fr.epsi.atelier_persistance.controllers;

import fr.epsi.atelier_persistance.entities.Adherent;
import fr.epsi.atelier_persistance.repositories.AdherentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adherents")
public class AdherentController {

    private final AdherentRepository adherentRepository;

    public AdherentController(AdherentRepository adherentRepository) {
        this.adherentRepository = adherentRepository;
    }

    @GetMapping
    public List<Adherent> getAllAdherents() {
        return adherentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adherent> getAdherentById(@PathVariable Long id) {
        Optional<Adherent> adherent = adherentRepository.findById(id);
        if (adherent.isPresent()) {
            return ResponseEntity.ok(adherent.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Adherent> createAdherent(@RequestBody Adherent adherent) {
        Adherent createdAdherent = adherentRepository.save(adherent);
        return new ResponseEntity<>(createdAdherent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adherent> updateAdherent(@PathVariable Long id, @RequestBody Adherent updatedAdherent) {
        Optional<Adherent> existingAdherent = adherentRepository.findById(id);
        if (existingAdherent.isPresent()) {
            Adherent adherent = existingAdherent.get();
            adherent.setNom(updatedAdherent.getNom());
            adherent.setPrenom(updatedAdherent.getPrenom());
            adherent.setEmail(updatedAdherent.getEmail());
            adherent.setDateInscription(updatedAdherent.getDateInscription());
            adherentRepository.save(adherent);
            return ResponseEntity.ok(adherent);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdherent(@PathVariable Long id) {
        Optional<Adherent> existingAdherent = adherentRepository.findById(id);
        if (existingAdherent.isPresent()) {
            adherentRepository.delete(existingAdherent.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
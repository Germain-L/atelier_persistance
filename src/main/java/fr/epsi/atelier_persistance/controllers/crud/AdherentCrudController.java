package fr.epsi.atelier_persistance.controllers.crud;

import fr.epsi.atelier_persistance.entities.Adherent;
import fr.epsi.atelier_persistance.repositories.AdherentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adherents")
public class AdherentCrudController {

    private final AdherentRepository adherentRepository;

    public AdherentCrudController(AdherentRepository adherentRepository) {
        this.adherentRepository = adherentRepository;
    }

    @GetMapping
    public List<Adherent> getAllAdherents() {
        return adherentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adherent> getAdherentById(@PathVariable Long id) {
        return adherentRepository.findById(id)
                .map(adherent -> ResponseEntity.ok().body(adherent))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Adherent createAdherent(@RequestBody Adherent adherent) {
        return adherentRepository.save(adherent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adherent> updateAdherent(@PathVariable Long id, @RequestBody Adherent adherentDetails) {
        return adherentRepository.findById(id)
                .map(adherent -> {
                    adherent.setNom(adherentDetails.getNom());
                    adherent.setPrenom(adherentDetails.getPrenom());
                    adherent.setEmail(adherentDetails.getEmail());
                    adherent.setDateInscription(adherentDetails.getDateInscription());
                    Adherent updatedAdherent = adherentRepository.save(adherent);
                    return ResponseEntity.ok().body(updatedAdherent);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdherent(@PathVariable Long id) {
        return adherentRepository.findById(id)
                .map(adherent -> {
                    adherentRepository.delete(adherent);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}

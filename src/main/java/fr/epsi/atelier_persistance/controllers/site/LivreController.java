package fr.epsi.atelier_persistance.controllers.site;

import fr.epsi.atelier_persistance.entities.Livre;
import fr.epsi.atelier_persistance.repositories.LivreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    private final LivreRepository livreRepository;

    public LivreController(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    @GetMapping
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livre> getLivreById(@PathVariable Long id) {
        Optional<Livre> livre = livreRepository.findById(id);
        if (livre.isPresent()) {
            return ResponseEntity.ok(livre.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Livre> createLivre(@RequestBody Livre livre) {
        Livre createdLivre = livreRepository.save(livre);
        return new ResponseEntity<>(createdLivre, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livre> updateLivre(@PathVariable Long id, @RequestBody Livre updatedLivre) {
        Optional<Livre> existingLivre = livreRepository.findById(id);
        if (existingLivre.isPresent()) {
            Livre livre = existingLivre.get();
            // Add code to update livre fields here
            // Example: livre.setTitre(updatedLivre.getTitre());
            livreRepository.save(livre);
            return ResponseEntity.ok(livre);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivre(@PathVariable Long id) {
        Optional<Livre> existingLivre = livreRepository.findById(id);
        if (existingLivre.isPresent()) {
            livreRepository.delete(existingLivre.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

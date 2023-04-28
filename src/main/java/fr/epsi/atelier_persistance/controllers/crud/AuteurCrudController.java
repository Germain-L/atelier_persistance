package fr.epsi.atelier_persistance.controllers.crud;

import fr.epsi.atelier_persistance.entities.Auteur;
import fr.epsi.atelier_persistance.repositories.AuteurRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auteurs")
public class AuteurCrudController {

    private final AuteurRepository auteurRepository;

    public AuteurCrudController(AuteurRepository auteurRepository) {
        this.auteurRepository = auteurRepository;
    }

    @GetMapping
    public List<Auteur> getAllAuteurs() {
        return auteurRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auteur> getAuteurById(@PathVariable Long id) {
        return auteurRepository.findById(id)
                .map(auteur -> ResponseEntity.ok().body(auteur))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Auteur createAuteur(@RequestBody Auteur auteur) {
        return auteurRepository.save(auteur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auteur> updateAuteur(@PathVariable Long id, @RequestBody Auteur auteurDetails) {
        return auteurRepository.findById(id)
                .map(auteur -> {
                    auteur.setNom(auteurDetails.getNom());
                    auteur.setPrenom(auteurDetails.getPrenom());
                    Auteur updatedAuteur = auteurRepository.save(auteur);
                    return ResponseEntity.ok().body(updatedAuteur);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuteur(@PathVariable Long id) {
        return auteurRepository.findById(id)
                .map(auteur -> {
                    auteurRepository.delete(auteur);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}

package fr.epsi.atelier_persistance.controllers.crud;

import fr.epsi.atelier_persistance.entities.Emprunt;
import fr.epsi.atelier_persistance.repositories.EmpruntRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprunts")
public class EmpruntCrudController {

    private final EmpruntRepository empruntRepository;

    public EmpruntCrudController(EmpruntRepository empruntRepository) {
        this.empruntRepository = empruntRepository;
    }

    @GetMapping
    public List<Emprunt> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprunt> getEmpruntById(@PathVariable Long id) {
        return empruntRepository.findById(id)
                .map(emprunt -> ResponseEntity.ok().body(emprunt))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Emprunt createEmprunt(@RequestBody Emprunt emprunt) {
        return empruntRepository.save(emprunt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprunt> updateEmprunt(@PathVariable Long id, @RequestBody Emprunt empruntDetails) {
        return empruntRepository.findById(id)
                .map(emprunt -> {
                    emprunt.setDateEmprunt(empruntDetails.getDateEmprunt());
                    emprunt.setDateFinPrevue(empruntDetails.getDateFinPrevue());
                    emprunt.setDateRetour(empruntDetails.getDateRetour());
                    emprunt.setAdherent(empruntDetails.getAdherent());
                    emprunt.setLivre(empruntDetails.getLivre());
                    Emprunt updatedEmprunt = empruntRepository.save(emprunt);
                    return ResponseEntity.ok().body(updatedEmprunt);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmprunt(@PathVariable Long id) {
        return empruntRepository.findById(id)
                .map(emprunt -> {
                    empruntRepository.delete(emprunt);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}

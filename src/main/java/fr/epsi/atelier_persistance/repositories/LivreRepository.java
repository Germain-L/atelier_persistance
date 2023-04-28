package fr.epsi.atelier_persistance.repositories;

import fr.epsi.atelier_persistance.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreRepository extends JpaRepository<Livre, Long> {
}

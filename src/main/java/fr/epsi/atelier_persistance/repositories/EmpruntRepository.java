package fr.epsi.atelier_persistance.repositories;

import fr.epsi.atelier_persistance.entities.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
}

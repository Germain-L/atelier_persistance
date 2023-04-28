package fr.epsi.atelier_persistance.repositories;

import fr.epsi.atelier_persistance.entities.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdherentRepository extends JpaRepository<Adherent, Long> {
}

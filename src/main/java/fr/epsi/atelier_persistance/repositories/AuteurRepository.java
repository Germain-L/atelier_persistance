package fr.epsi.atelier_persistance.repositories;

import fr.epsi.atelier_persistance.entities.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {
}
package fr.epsi.atelier_persistance.repositories;

import fr.epsi.atelier_persistance.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}

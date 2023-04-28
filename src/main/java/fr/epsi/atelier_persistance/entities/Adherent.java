package fr.epsi.atelier_persistance.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Adherent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Date dateInscription;

    @OneToMany(mappedBy = "adherent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Emprunt> emprunts;
}
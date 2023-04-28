package fr.epsi.atelier_persistance.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date dateEmprunt;

    @Column(nullable = false)
    private Date dateFinPrevue;

    private Date dateRetour;

    @ManyToOne
    @JoinColumn(name = "adherent_id", nullable = false)
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "livre_id", nullable = false)
    private Livre livre;
}
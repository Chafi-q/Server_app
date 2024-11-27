package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomComplet;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private String lieuDeNaissance;
    private String sexe;
    private String typePieceIdentite;
    private String numPieceIdentite;
    private String adresse;
    private String numTel;
    private String email;
    private String visiblePour;
}

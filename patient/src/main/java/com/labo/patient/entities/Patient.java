package com.labo.patient.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomComplet;

    private LocalDate dateNaissance;

    private String lieuNaissance;

    private String sexe;

    private String typePieceIdentite;

    private String numPieceIdentite;

    private String adresse;

    private String numTel;

    private String email;

    private Boolean visiblePour;
}
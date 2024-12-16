package com.labo.patient.dtos;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class PatientDTO {
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
package com.example.demo.dtos;

import lombok.Data;

@Data
public class PatientDTO {
    private Long id;
    private String nomComplet;
    private String dateNaissance; // Format YYYY-MM-DD
    private String lieuDeNaissance;
    private String sexe;
    private String typePieceIdentite;
    private String numPieceIdentite;
    private String adresse;
    private String numTel;
    private String email;
    private String visiblePour;
}

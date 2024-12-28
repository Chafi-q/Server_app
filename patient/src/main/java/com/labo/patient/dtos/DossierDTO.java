package com.labo.patient.dtos;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DossierDTO {
    private Long numDossier;
    private String fkEmailUtilisateur;
    private Long fkIdPassion;
    private LocalDate date;

    public Long getNumDossier() {
        return numDossier;
    }

    public void setNumDossier(Long numDossier) {
        this.numDossier = numDossier;
    }

    public String getFkEmailUtilisateur() {
        return fkEmailUtilisateur;
    }

    public void setFkEmailUtilisateur(String fkEmailUtilisateur) {
        this.fkEmailUtilisateur = fkEmailUtilisateur;
    }

    public Long getFkIdPassion() {
        return fkIdPassion;
    }

    public void setFkIdPassion(Long fkIdPassion) {
        this.fkIdPassion = fkIdPassion;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
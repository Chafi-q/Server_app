package com.labo.patient.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "dossier")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numDossier;

    private String fkEmailUtilisateur; // Foreign key to user email
    private Long fkIdPassion;          // Foreign key to passion ID
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
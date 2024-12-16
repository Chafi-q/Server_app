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
}
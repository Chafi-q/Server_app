package com.labo.examin.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "examen")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkNumDossier;
    private Long fkIdEpreuve;
    private Long fkIdTestAnalyse;
    private String resultat;
}
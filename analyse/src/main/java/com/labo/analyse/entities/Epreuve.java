package com.labo.analyse.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "epreuve")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkIdAnalyse;
    private String nom;
}
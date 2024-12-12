package com.labo.laboratoire.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "adresse")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numVoie;
    private String nomVoie;
    private String codePostal;
    private String ville;
    private String commune;
}
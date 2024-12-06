package com.labo.laboratoire.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Laboratoire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String logo;

    @Column(nullable = false, unique = true)
    private String nrc;

    private boolean active;

    private LocalDate dateActivation;

	public Laboratoire(String laboratoireA, String image, String nrc123, boolean b, LocalDate now) {
	}
}

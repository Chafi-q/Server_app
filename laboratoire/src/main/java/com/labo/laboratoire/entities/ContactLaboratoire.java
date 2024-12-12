package com.labo.laboratoire.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "contactLaboratoire")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContactLaboratoire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkIdLaboratoire;

    private Long fkIdAdresse;

    private String numTel;

    private String fax;

    private String email;
}
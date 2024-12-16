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
}
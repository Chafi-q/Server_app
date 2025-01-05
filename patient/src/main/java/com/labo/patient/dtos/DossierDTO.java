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
    private LocalDate date;
    private Long patientId; // References the associated patient's ID
}
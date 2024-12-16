package com.labo.examin.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExamenDTO {
    private Long id;
    private Long fkNumDossier;
    private Long fkIdEpreuve;
    private Long fkIdTestAnalyse;
    private String resultat;
}
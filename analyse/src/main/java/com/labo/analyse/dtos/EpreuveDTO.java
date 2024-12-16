package com.labo.analyse.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EpreuveDTO {
    private Long id;
    private Long fkIdAnalyse;
    private String nom;
}
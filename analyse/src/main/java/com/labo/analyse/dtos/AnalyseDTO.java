package com.labo.analyse.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnalyseDTO {

    private Long id;
    private Long fkIdLaboratoire;
    private String nom;
    private String description;

}

package com.labo.analyse.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LaboratoireDTO {
    private Long id;
    private String nom;
    private String adresse;
    private String contact;
}

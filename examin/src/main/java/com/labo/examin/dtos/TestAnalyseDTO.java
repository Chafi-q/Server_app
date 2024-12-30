package com.labo.examin.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestAnalyseDTO {
    private Long id;
    private String nom;
    private String description;
    private String type;

}

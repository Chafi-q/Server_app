package com.labo.utilisateur.dtos;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaboratoireDTO {
    private Long id;
    private String nom;
    private String logo;
    private boolean active;
}

package com.labo.laboratoire.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContactDTO {
    private Long id;
    private Long fkIdLaboratoire;
    private Long fkIdAdresse;
    private String numTel;
    private String fax;
    private String email;
}
package com.labo.test.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestDTO {
    private Long id;
    private Long fkIdAnalyse;
    private String nomTest;
    private String sousEpreuve;
    private String intervalMinDeReference;
    private String intervalMaxDeReference;
    private String uniteDeReference;
    private String details;
}
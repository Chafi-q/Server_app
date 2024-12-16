package com.labo.test.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "testAnalyse")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkIdAnalyse;
    private String nomTest;
    private String sousEpreuve;
    private String intervalMinDeReference;
    private String intervalMaxDeReference;
    private String uniteDeReference;
    private String details;
}
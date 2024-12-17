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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFkIdAnalyse() {
        return fkIdAnalyse;
    }

    public void setFkIdAnalyse(Long fkIdAnalyse) {
        this.fkIdAnalyse = fkIdAnalyse;
    }

    public String getNomTest() {
        return nomTest;
    }

    public void setNomTest(String nomTest) {
        this.nomTest = nomTest;
    }

    public String getSousEpreuve() {
        return sousEpreuve;
    }

    public void setSousEpreuve(String sousEpreuve) {
        this.sousEpreuve = sousEpreuve;
    }

    public String getIntervalMinDeReference() {
        return intervalMinDeReference;
    }

    public void setIntervalMinDeReference(String intervalMinDeReference) {
        this.intervalMinDeReference = intervalMinDeReference;
    }

    public String getIntervalMaxDeReference() {
        return intervalMaxDeReference;
    }

    public void setIntervalMaxDeReference(String intervalMaxDeReference) {
        this.intervalMaxDeReference = intervalMaxDeReference;
    }

    public String getUniteDeReference() {
        return uniteDeReference;
    }

    public void setUniteDeReference(String uniteDeReference) {
        this.uniteDeReference = uniteDeReference;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
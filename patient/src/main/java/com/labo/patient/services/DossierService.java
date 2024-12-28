package com.labo.patient.services;

import com.labo.patient.dtos.DossierDTO;

import java.util.List;

public interface DossierService {

    DossierDTO saveDossier(DossierDTO dto);

    List<DossierDTO> getAllDossiers();

    DossierDTO getDossierById(Long id);
    void deleteDossier(Long id);
}
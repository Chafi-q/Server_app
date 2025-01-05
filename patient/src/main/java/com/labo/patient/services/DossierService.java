package com.labo.patient.services;

import com.labo.patient.dtos.DossierDTO;
import com.labo.patient.entities.Dossier;

import java.time.LocalDate;
import java.util.List;

public interface DossierService {

    Dossier createDossier(Long patientId, LocalDate date);
    Dossier getDossierByPatientId(Long patientId);
    void deleteDossier(Long dossierId);
    List<Dossier> getAllDossiers();
    Dossier getDossierById(Long id);
}
package com.labo.patient.services.Impl;

import com.labo.patient.dtos.DossierDTO;
import com.labo.patient.entities.Dossier;
import com.labo.patient.entities.Patient;
import com.labo.patient.repositories.DossierRepository;
import com.labo.patient.repositories.PatientRepository;
import com.labo.patient.services.DossierService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DossierServiceImpl implements DossierService {

    @Autowired
    private final DossierRepository dossierRepository;
    private final PatientRepository patientRepository;

    public Dossier createDossier(Long patientId, LocalDate date) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + patientId));

        if (patient.getDossier() != null) {
            throw new IllegalStateException("Patient already has a dossier");
        }

        Dossier dossier = new Dossier();
        dossier.setPatient(patient);
        dossier.setDate(date);

        return dossierRepository.save(dossier);
    }

    public Dossier getDossierByPatientId(Long patientId) {
        return dossierRepository.findByPatientId(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Dossier not found for patient ID: " + patientId));
    }

    public void deleteDossier(Long dossierId) {
        if (!dossierRepository.existsById(dossierId)) {
            throw new EntityNotFoundException("Dossier not found with ID: " + dossierId);
        }
        dossierRepository.deleteById(dossierId);
    }

    public List<Dossier> getAllDossiers() {
        return dossierRepository.findAll();
    }

    public Dossier getDossierById(Long id) {
        return dossierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dossier not found with ID: " + id));
    }

}
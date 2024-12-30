package com.labo.patient.services.Impl;

import Patient.src.main.java.com.labo.patient.FeignClient.PatientClient;
import com.labo.patient.dtos.DossierDTO;
import com.labo.patient.entities.Dossier;
import com.labo.patient.repositories.DossierRepository;
import com.labo.patient.services.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DossierServiceImpl implements DossierService {

    @Autowired
    private DossierRepository repository;

    private  PatientClient patientClient;

    public DossierServiceImpl(PatientClient patientClient) {
        this.patientClient = patientClient;
    }

    public com.labo.patient.dtos.PatientDTO getPatientDetails(Long fkIdPatient) {
        return patientClient.getPatientById(fkIdPatient);
    }


    public DossierDTO saveDossier(DossierDTO dto) {
        Dossier entity = mapToEntity(dto);
        Dossier savedEntity = repository.save(entity);
        return mapToDTO(savedEntity);
    }

    public List<DossierDTO> getAllDossiers() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public DossierDTO getDossierById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Dossier not found"));
    }

    public void deleteDossier(Long id) {
        repository.deleteById(id);
    }

    private Dossier mapToEntity(DossierDTO dto) {
        Dossier entity = new Dossier();
        entity.setNumDossier(dto.getNumDossier());
        entity.setFkEmailUtilisateur(dto.getFkEmailUtilisateur());
        entity.setFkIdPassion(dto.getFkIdPassion());
        entity.setDate(dto.getDate());
        return entity;
    }

    private DossierDTO mapToDTO(Dossier entity) {
        DossierDTO dto = new DossierDTO();
        dto.setNumDossier(entity.getNumDossier());
        dto.setFkEmailUtilisateur(entity.getFkEmailUtilisateur());
        dto.setFkIdPassion(entity.getFkIdPassion());
        dto.setDate(entity.getDate());
        return dto;
    }
}
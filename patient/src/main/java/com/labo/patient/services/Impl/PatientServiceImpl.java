package com.labo.patient.services.Impl;

import com.labo.patient.dtos.PatientDTO;
import com.labo.patient.entities.Patient;
import com.labo.patient.repositories.PatientRepository;
import com.labo.patient.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = mapToEntity(patientDTO);
        Patient savedPatient = patientRepository.save(patient);
        return mapToDTO(savedPatient);
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        return patientRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setNomComplet(patientDTO.getNomComplet());
        patient.setDateNaissance(patientDTO.getDateNaissance());
        patient.setLieuNaissance(patientDTO.getLieuNaissance());
        patient.setSexe(patientDTO.getSexe());
        patient.setTypePieceIdentite(patientDTO.getTypePieceIdentite());
        patient.setNumPieceIdentite(patientDTO.getNumPieceIdentite());
        patient.setAdresse(patientDTO.getAdresse());
        patient.setNumTel(patientDTO.getNumTel());
        patient.setEmail(patientDTO.getEmail());
        patient.setVisiblePour(patientDTO.getVisiblePour());

        Patient updatedPatient = patientRepository.save(patient);
        return mapToDTO(updatedPatient);
    }

    @Override
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found");
        }
        patientRepository.deleteById(id);
    }

    private Patient mapToEntity(PatientDTO dto) {
        Patient entity = new Patient();
        entity.setNomComplet(dto.getNomComplet());
        entity.setDateNaissance(dto.getDateNaissance());
        entity.setLieuNaissance(dto.getLieuNaissance());
        entity.setSexe(dto.getSexe());
        entity.setTypePieceIdentite(dto.getTypePieceIdentite());
        entity.setNumPieceIdentite(dto.getNumPieceIdentite());
        entity.setAdresse(dto.getAdresse());
        entity.setNumTel(dto.getNumTel());
        entity.setEmail(dto.getEmail());
        entity.setVisiblePour(dto.getVisiblePour());
        return entity;
    }

    private PatientDTO mapToDTO(Patient entity) {
        PatientDTO dto = new PatientDTO();
        dto.setId(entity.getId());
        dto.setNomComplet(entity.getNomComplet());
        dto.setDateNaissance(entity.getDateNaissance());
        dto.setLieuNaissance(entity.getLieuNaissance());
        dto.setSexe(entity.getSexe());
        dto.setTypePieceIdentite(entity.getTypePieceIdentite());
        dto.setNumPieceIdentite(entity.getNumPieceIdentite());
        dto.setAdresse(entity.getAdresse());
        dto.setNumTel(entity.getNumTel());
        dto.setEmail(entity.getEmail());
        dto.setVisiblePour(entity.getVisiblePour());
        return dto;
    }
}
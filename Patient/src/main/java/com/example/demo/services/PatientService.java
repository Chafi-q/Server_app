package com.example.demo.services;

import com.example.demo.entities.PatientEntity;
import com.example.demo.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientEntity> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<PatientEntity> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public PatientEntity savePatient(PatientEntity patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}

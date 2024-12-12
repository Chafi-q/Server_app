package com.labo.patient;

import com.labo.patient.dtos.PatientDTO;
import com.labo.patient.entities.Patient;
import com.labo.patient.repositories.PatientRepository;
import com.labo.patient.services.Impl.PatientServiceImpl;
import com.labo.patient.services.PatientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PatientServiceTest {
    private final PatientRepository patientRepository = Mockito.mock(PatientRepository.class);
    private final ModelMapper modelMapper = new ModelMapper();
    private final PatientService patientService = new PatientServiceImpl(patientRepository, modelMapper);

    @Test
    void createPatient() {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setNomComplet("John Doe");
        patientDTO.setDateNaissance(LocalDate.of(1985, 5, 20));

        Patient patient = new Patient();
        patient.setId(1L);
        patient.setNomComplet("John Doe");
        patient.setDateNaissance(LocalDate.of(1985, 5, 20));

        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        PatientDTO result = patientService.createPatient(patientDTO);
        assertNotNull(result);
        assertEquals("John Doe", result.getNomComplet());
    }

    @Test
    void getPatientById() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setNomComplet("John Doe");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        PatientDTO result = patientService.getPatientById(1L);
        assertNotNull(result);
        assertEquals("John Doe", result.getNomComplet());
    }
}
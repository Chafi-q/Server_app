package com.labo.patient;

import com.labo.patient.dtos.PatientDTO;
import com.labo.patient.entities.Patient;
import com.labo.patient.repositories.PatientRepository;
import com.labo.patient.services.Impl.PatientServiceImpl;
import com.labo.patient.services.PatientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class PatientServiceTest {
    private final PatientRepository patientRepository = Mockito.mock(PatientRepository.class);
    private final PatientService patientService = new PatientServiceImpl();

    @Test
    void createPatient() {
        PatientDTO patientDTO = PatientDTO.builder()
                .nomComplet("John Doe")
                .dateNaissance(LocalDate.of(1985, 5, 20))
                .build();

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

    @Test
    void updatePatient() {
        PatientDTO patientDTO = PatientDTO.builder()
                .nomComplet("Jane Doe")
                .dateNaissance(LocalDate.of(1990, 7, 15))
                .build();

        Patient patient = new Patient();
        patient.setId(1L);
        patient.setNomComplet("John Doe");
        patient.setDateNaissance(LocalDate.of(1985, 5, 20));

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(patientRepository.save(any(Patient.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PatientDTO result = patientService.updatePatient(1L, patientDTO);
        assertNotNull(result);
        assertEquals("Jane Doe", result.getNomComplet());
        assertEquals(LocalDate.of(1990, 7, 15), result.getDateNaissance());
    }

    @Test
    void deletePatient() {
        when(patientRepository.existsById(1L)).thenReturn(true);

        patientService.deletePatient(1L);

        Mockito.verify(patientRepository).deleteById(1L);
    }

    @Test
    void deletePatientNotFound() {
        when(patientRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            patientService.deletePatient(1L);
        });

        assertEquals("Patient not found", exception.getMessage());
    }
}

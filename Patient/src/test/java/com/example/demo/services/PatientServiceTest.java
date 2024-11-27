package com.example.demo.services;

import com.example.demo.entities.PatientEntity;
import com.example.demo.repositories.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private PatientEntity patient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patient = new PatientEntity();
        patient.setId(1L);
        patient.setNomComplet("Karim Karimi");
        patient.setEmail("karim.kar‎imi@example.com");
    }

    @Test
    void getAllPatients_ShouldReturnListOfPatients() {
        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient));
        assertEquals(1, patientService.getAllPatients().size());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void getPatientById_ShouldReturnPatient_WhenIdExists() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        Optional<PatientEntity> result = patientService.getPatientById(1L);
        assertTrue(result.isPresent());
        assertEquals("Karim Karimi", result.get().getNomComplet());
    }

    @Test
    void getPatientById_ShouldReturnEmpty_WhenIdDoesNotExist() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<PatientEntity> result = patientService.getPatientById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    void savePatient_ShouldReturnSavedPatient() {
        when(patientRepository.save(patient)).thenReturn(patient);
        PatientEntity result = patientService.savePatient(patient);
        assertNotNull(result);
        assertEquals("Karim Karimi", result.getNomComplet());
    }

    @Test
    void deletePatient_ShouldCallRepositoryDelete() {
        doNothing().when(patientRepository).deleteById(1L);
        patientService.deletePatient(1L);
        verify(patientRepository, times(1)).deleteById(1L);
    }
}

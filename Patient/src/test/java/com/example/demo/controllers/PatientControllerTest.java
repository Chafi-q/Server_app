package com.example.demo.controllers;

import com.example.demo.entities.PatientEntity;
import com.example.demo.services.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    private PatientEntity patient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patient = new PatientEntity();
        patient.setId(1L);
        patient.setNomComplet("Karim Karimi");
        patient.setEmail("karim.karimi@example.com");
    }

    @Test
    void getAllPatients_ShouldReturnListOfPatients() {
        when(patientService.getAllPatients()).thenReturn(Arrays.asList(patient));
        assertEquals(1, patientController.getAllPatients().size());
        verify(patientService, times(1)).getAllPatients();
    }

    @Test
    void getPatientById_ShouldReturnPatient_WhenIdExists() {
        when(patientService.getPatientById(1L)).thenReturn(Optional.of(patient));
        ResponseEntity<PatientEntity> response = patientController.getPatientById(1L);
        assertEquals(OK, response.getStatusCode());
        assertEquals("Karim Karimi", response.getBody().getNomComplet());
    }

    @Test
    void getPatientById_ShouldReturnNotFound_WhenIdDoesNotExist() {
        when(patientService.getPatientById(1L)).thenReturn(Optional.empty());
        ResponseEntity<PatientEntity> response = patientController.getPatientById(1L);
        assertEquals(NOT_FOUND, response.getStatusCode());
    }

    @Test
    void createPatient_ShouldReturnSavedPatient() {
        when(patientService.savePatient(patient)).thenReturn(patient);
        PatientEntity response = patientController.createPatient(patient);
        assertNotNull(response);
        assertEquals("Karim Karimi", response.getNomComplet());
    }

    @Test
    void updatePatient_ShouldReturnUpdatedPatient_WhenIdExists() {
        when(patientService.getPatientById(1L)).thenReturn(Optional.of(patient));
        when(patientService.savePatient(patient)).thenReturn(patient);

        ResponseEntity<PatientEntity> response = patientController.updatePatient(1L, patient);
        assertEquals(OK, response.getStatusCode());
        assertEquals("Karim Karimi", response.getBody().getNomComplet());
    }

    @Test
    void updatePatient_ShouldReturnNotFound_WhenIdDoesNotExist() {
        when(patientService.getPatientById(1L)).thenReturn(Optional.empty());
        ResponseEntity<PatientEntity> response = patientController.updatePatient(1L, patient);
        assertEquals(NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deletePatient_ShouldReturnNoContent_WhenIdExists() {
        when(patientService.getPatientById(1L)).thenReturn(Optional.of(patient));
        doNothing().when(patientService).deletePatient(1L);

        ResponseEntity<Void> response = patientController.deletePatient(1L);
        assertEquals(NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deletePatient_ShouldReturnNotFound_WhenIdDoesNotExist() {
        when(patientService.getPatientById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Void> response = patientController.deletePatient(1L);
        assertEquals(NOT_FOUND, response.getStatusCode());
    }
}

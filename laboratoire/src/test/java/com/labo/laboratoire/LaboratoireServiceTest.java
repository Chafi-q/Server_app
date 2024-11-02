package com.labo.laboratoire;


import entities.Laboratoire;
import services.LaboratoireService;
import Repositories.LaboratoireRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaboratoireServiceTest {

    @InjectMocks
    private LaboratoireService laboratoireService;

    @Mock
    private LaboratoireRepository laboratoireRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateLaboratoire() {
        Laboratoire laboratoire = new Laboratoire("Laboratoire A", "logoA.png", "NRC123", true, LocalDate.now());
        when(laboratoireRepository.save(laboratoire)).thenReturn(laboratoire);

        Laboratoire createdLaboratoire = laboratoireService.createLaboratoire(laboratoire);

        assertNotNull(createdLaboratoire);
        assertEquals("Laboratoire A", createdLaboratoire.getNom());
        verify(laboratoireRepository, times(1)).save(laboratoire);
    }

    @Test
    void testGetLaboratoireById() {
        Laboratoire laboratoire = new Laboratoire("Laboratoire A", "logoA.png", "NRC123", true, LocalDate.now());
        when(laboratoireRepository.findById(1L)).thenReturn(Optional.of(laboratoire));

        Optional<Laboratoire> foundLaboratoire = laboratoireService.getLaboratoireById(1L);

        assertTrue(foundLaboratoire.isPresent());
        assertEquals("Laboratoire A", foundLaboratoire.get().getNom());
        verify(laboratoireRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateLaboratoire() {
        Laboratoire laboratoire = new Laboratoire("Laboratoire A", "logoA.png", "NRC123", true, LocalDate.now());
        when(laboratoireRepository.findById(1L)).thenReturn(Optional.of(laboratoire));
        when(laboratoireRepository.save(laboratoire)).thenReturn(laboratoire);

        Laboratoire updatedLaboratoire = laboratoireService.updateLaboratoire(1L, laboratoire);

        assertNotNull(updatedLaboratoire);
        assertEquals("Laboratoire A", updatedLaboratoire.getNom());
        verify(laboratoireRepository, times(1)).findById(1L);
        verify(laboratoireRepository, times(1)).save(laboratoire);
    }

    @Test
    void testDeleteLaboratoire() {
        laboratoireService.deleteLaboratoire(1L);
        verify(laboratoireRepository, times(1)).deleteById(1L);
    }
}

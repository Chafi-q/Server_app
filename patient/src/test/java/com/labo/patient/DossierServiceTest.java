package com.labo.patient;

import com.labo.patient.dtos.DossierDTO;
import com.labo.patient.entities.Dossier;
import com.labo.patient.repositories.DossierRepository;
import com.labo.patient.services.DossierService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DossierServiceTest {

    @InjectMocks
    private DossierService service;

    @Mock
    private DossierRepository repository;

    public DossierServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDossier() {
        DossierDTO dto = new DossierDTO();
        dto.setFkEmailUtilisateur("user@example.com");
        dto.setFkIdPassion(2L);
        dto.setDate(LocalDate.now());

        Dossier entity = new Dossier();
        entity.setFkEmailUtilisateur("user@example.com");
        entity.setFkIdPassion(2L);
        entity.setDate(LocalDate.now());

        when(repository.save(any(Dossier.class))).thenReturn(entity);

        DossierDTO result = service.saveDossier(dto);

        assertEquals(dto.getFkEmailUtilisateur(), result.getFkEmailUtilisateur());
        verify(repository, times(1)).save(any(Dossier.class));
    }

    @Test
    public void testGetDossierById() {
        Dossier entity = new Dossier();
        entity.setNumDossier(1L);
        entity.setFkEmailUtilisateur("user@example.com");
        entity.setFkIdPassion(2L);
        entity.setDate(LocalDate.now());

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        DossierDTO result = service.getDossierById(1L);

        assertEquals(entity.getFkEmailUtilisateur(), result.getFkEmailUtilisateur());
        verify(repository, times(1)).findById(1L);
    }
}
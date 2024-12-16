package com.labo.examin;

import com.labo.examin.dtos.ExamenDTO;
import com.labo.examin.entities.Examen;
import com.labo.examin.repositories.ExamenRepository;
import com.labo.examin.services.ExamenService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ExamenServiceTest {

    @InjectMocks
    private ExamenService service;

    @Mock
    private ExamenRepository repository;

    public ExamenServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveExamen() {
        ExamenDTO dto = new ExamenDTO();
        dto.setFkNumDossier(1L);
        dto.setFkIdEpreuve(2L);
        dto.setFkIdTestAnalyse(3L);
        dto.setResultat("Positive");

        Examen entity = new Examen();
        entity.setFkNumDossier(1L);
        entity.setFkIdEpreuve(2L);
        entity.setFkIdTestAnalyse(3L);
        entity.setResultat("Positive");

        when(repository.save(any(Examen.class))).thenReturn(entity);

        ExamenDTO result = service.saveExamen(dto);

        assertEquals(dto.getResultat(), result.getResultat());
        verify(repository, times(1)).save(any(Examen.class));
    }

    @Test
    public void testGetExamenById() {
        Examen entity = new Examen();
        entity.setId(1L);
        entity.setFkNumDossier(1L);
        entity.setFkIdEpreuve(2L);
        entity.setFkIdTestAnalyse(3L);
        entity.setResultat("Positive");

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        ExamenDTO result = service.getExamenById(1L);

        assertEquals(entity.getResultat(), result.getResultat());
        verify(repository, times(1)).findById(1L);
    }
}

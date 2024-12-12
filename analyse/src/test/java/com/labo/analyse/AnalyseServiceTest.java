package com.labo.analyse;

import com.labo.analyse.dtos.AnalyseDTO;
import com.labo.analyse.entities.Analyse;
import com.labo.analyse.repositories.AnalyseRepository;
import com.labo.analyse.services.AnalyseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AnalyseServiceTest {

    @InjectMocks
    private AnalyseService service;

    @Mock
    private AnalyseRepository repository;

    public AnalyseServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveAnalyse() {
        AnalyseDTO dto = new AnalyseDTO();
        dto.setFkIdLaboratoire(1L);
        dto.setNom("Analyse 1");
        dto.setDescription("Description 1");

        Analyse entity = new Analyse();
        entity.setFkIdLaboratoire(1L);
        entity.setNom("Analyse 1");
        entity.setDescription("Description 1");

        when(repository.save(any(Analyse.class))).thenReturn(entity);

        AnalyseDTO result = service.saveAnalyse(dto);

        assertEquals(dto.getNom(), result.getNom());
        verify(repository, times(1)).save(any(Analyse.class));
    }

    @Test
    public void testGetAnalyseById() {
        Analyse entity = new Analyse();
        entity.setId(1L);
        entity.setFkIdLaboratoire(1L);
        entity.setNom("Analyse 1");
        entity.setDescription("Description 1");

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        AnalyseDTO result = service.getAnalyseById(1L);

        assertEquals(entity.getNom(), result.getNom());
        verify(repository, times(1)).findById(1L);
    }
}

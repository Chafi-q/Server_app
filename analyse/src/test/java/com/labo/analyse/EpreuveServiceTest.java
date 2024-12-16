package com.labo.analyse;

import com.labo.analyse.dtos.EpreuveDTO;
import com.labo.analyse.entities.Epreuve;
import com.labo.analyse.repositories.EpreuveRepository;
import com.labo.analyse.services.EpreuveService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EpreuveServiceTest {

    @InjectMocks
    private EpreuveService service;

    @Mock
    private EpreuveRepository repository;

    public EpreuveServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveEpreuve() {
        EpreuveDTO dto = new EpreuveDTO();
        dto.setFkIdAnalyse(1L);
        dto.setNom("Epreuve 1");

        Epreuve entity = new Epreuve();
        entity.setFkIdAnalyse(1L);
        entity.setNom("Epreuve 1");

        when(repository.save(any(Epreuve.class))).thenReturn(entity);

        EpreuveDTO result = service.saveEpreuve(dto);

        assertEquals(dto.getNom(), result.getNom());
        verify(repository, times(1)).save(any(Epreuve.class));
    }

    @Test
    public void testGetEpreuveById() {
        Epreuve entity = new Epreuve();
        entity.setId(1L);
        entity.setFkIdAnalyse(1L);
        entity.setNom("Epreuve 1");

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        EpreuveDTO result = service.getEpreuveById(1L);

        assertEquals(entity.getNom(), result.getNom());
        verify(repository, times(1)).findById(1L);
    }
}

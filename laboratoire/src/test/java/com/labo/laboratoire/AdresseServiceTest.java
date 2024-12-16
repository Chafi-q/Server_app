package com.labo.laboratoire;

import com.labo.laboratoire.Repositories.AdresseRepository;
import com.labo.laboratoire.dtos.AdresseDTO;
import com.labo.laboratoire.entities.Adresse;
import com.labo.laboratoire.services.AdresseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdresseServiceTest {

    @InjectMocks
    private AdresseService service;

    @Mock
    private AdresseRepository repository;

    public AdresseServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveAdresse() {
        AdresseDTO dto = new AdresseDTO();
        dto.setNumVoie("123");
        dto.setNomVoie("Main Street");
        dto.setCodePostal("12345");
        dto.setVille("City");
        dto.setCommune("Commune");

        Adresse entity = new Adresse();
        entity.setNumVoie("123");
        entity.setNomVoie("Main Street");
        entity.setCodePostal("12345");
        entity.setVille("City");
        entity.setCommune("Commune");

        when(repository.save(any(Adresse.class))).thenReturn(entity);

        AdresseDTO result = service.saveAdresse(dto);

        assertEquals(dto.getNomVoie(), result.getNomVoie());
        verify(repository, times(1)).save(any(Adresse.class));
    }

    @Test
    public void testGetAdresseById() {
        Adresse entity = new Adresse();
        entity.setId(1L);
        entity.setNumVoie("123");
        entity.setNomVoie("Main Street");
        entity.setCodePostal("12345");
        entity.setVille("City");
        entity.setCommune("Commune");

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        AdresseDTO result = service.getAdresseById(1L);

        assertEquals(entity.getNomVoie(), result.getNomVoie());
        verify(repository, times(1)).findById(1L);
    }
}
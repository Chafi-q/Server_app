package com.labo.laboratoire;

import com.labo.laboratoire.Repositories.ContactLaboratoireRepository;
import com.labo.laboratoire.dtos.ContactLaboratoireDTO;
import com.labo.laboratoire.entities.ContactLaboratoire;
import com.labo.laboratoire.services.ContactLaboratoireService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ContactLaboratoireServiceTest {

    @InjectMocks
    private ContactLaboratoireService service;

    @Mock
    private ContactLaboratoireRepository repository;

    public ContactLaboratoireServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveContact() {
        ContactLaboratoireDTO dto = new ContactLaboratoireDTO();
        dto.setFkIdLaboratoire(1L);
        dto.setFkIdAdresse(2L);
        dto.setNumTel("123456789");
        dto.setFax("123456");
        dto.setEmail("example@test.com");

        ContactLaboratoire entity = new ContactLaboratoire();
        entity.setFkIdLaboratoire(1L);
        entity.setFkIdAdresse(2L);
        entity.setNumTel("123456789");
        entity.setFax("123456");
        entity.setEmail("example@test.com");

        when(repository.save(any(ContactLaboratoire.class))).thenReturn(entity);

        ContactLaboratoireDTO result = service.saveContact(dto);

        assertEquals(dto.getNumTel(), result.getNumTel());
        verify(repository, times(1)).save(any(ContactLaboratoire.class));
    }

    @Test
    public void testGetContactById() {
        ContactLaboratoire entity = new ContactLaboratoire();
        entity.setId(1L);
        entity.setFkIdLaboratoire(1L);
        entity.setFkIdAdresse(2L);
        entity.setNumTel("123456789");
        entity.setFax("123456");
        entity.setEmail("example@test.com");

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        ContactLaboratoireDTO result = service.getContactById(1L);

        assertEquals(entity.getEmail(), result.getEmail());
        verify(repository, times(1)).findById(1L);
    }
}
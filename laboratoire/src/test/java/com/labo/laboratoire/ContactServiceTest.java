package com.labo.laboratoire;

import com.labo.laboratoire.Repositories.ContactRepository;
import com.labo.laboratoire.dtos.ContactDTO;
import com.labo.laboratoire.entities.Contact;
import com.labo.laboratoire.services.ContactService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ContactServiceTest {

    @InjectMocks
    private ContactService service;

    @Mock
    private ContactRepository repository;

    public ContactServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveContact() {
        ContactDTO dto = new ContactDTO();
        dto.setFkIdLaboratoire(1L);
        dto.setFkIdAdresse(2L);
        dto.setNumTel("123456789");
        dto.setFax("123456");
        dto.setEmail("example@test.com");

        Contact entity = new Contact();
        entity.setFkIdLaboratoire(1L);
        entity.setFkIdAdresse(2L);
        entity.setNumTel("123456789");
        entity.setFax("123456");
        entity.setEmail("example@test.com");

        when(repository.save(any(Contact.class))).thenReturn(entity);

        ContactDTO result = service.saveContact(dto);

        assertEquals(dto.getNumTel(), result.getNumTel());
        verify(repository, times(1)).save(any(Contact.class));
    }

    @Test
    public void testGetContactById() {
        Contact entity = new Contact();
        entity.setId(1L);
        entity.setFkIdLaboratoire(1L);
        entity.setFkIdAdresse(2L);
        entity.setNumTel("123456789");
        entity.setFax("123456");
        entity.setEmail("example@test.com");

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        ContactDTO result = service.getContactById(1L);

        assertEquals(entity.getEmail(), result.getEmail());
        verify(repository, times(1)).findById(1L);
    }
}
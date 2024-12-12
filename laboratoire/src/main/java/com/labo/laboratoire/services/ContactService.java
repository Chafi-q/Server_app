package com.labo.laboratoire.services;

import com.labo.laboratoire.Repositories.ContactRepository;
import com.labo.laboratoire.dtos.ContactDTO;
import com.labo.laboratoire.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public ContactDTO saveContact(ContactDTO dto) {
        Contact entity = mapToEntity(dto);
        Contact savedEntity = repository.save(entity);
        return mapToDTO(savedEntity);
    }

    public List<ContactDTO> getAllContacts() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ContactDTO getContactById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("ContactLaboratoire not found"));
    }

    public void deleteContact(Long id) {
        repository.deleteById(id);
    }

    private Contact mapToEntity(ContactDTO dto) {
        Contact entity = new Contact();
        entity.setId(dto.getId());
        entity.setFkIdLaboratoire(dto.getFkIdLaboratoire());
        entity.setFkIdAdresse(dto.getFkIdAdresse());
        entity.setNumTel(dto.getNumTel());
        entity.setFax(dto.getFax());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    private ContactDTO mapToDTO(Contact entity) {
        ContactDTO dto = new ContactDTO();
        dto.setId(entity.getId());
        dto.setFkIdLaboratoire(entity.getFkIdLaboratoire());
        dto.setFkIdAdresse(entity.getFkIdAdresse());
        dto.setNumTel(entity.getNumTel());
        dto.setFax(entity.getFax());
        dto.setEmail(entity.getEmail());
        return dto;
    }
}
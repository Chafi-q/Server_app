package com.labo.laboratoire.services;

import com.labo.laboratoire.Repositories.ContactLaboratoireRepository;
import com.labo.laboratoire.dtos.ContactLaboratoireDTO;
import com.labo.laboratoire.entities.ContactLaboratoire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactLaboratoireService {

    @Autowired
    private ContactLaboratoireRepository repository;

    public ContactLaboratoireDTO saveContact(ContactLaboratoireDTO dto) {
        ContactLaboratoire entity = mapToEntity(dto);
        ContactLaboratoire savedEntity = repository.save(entity);
        return mapToDTO(savedEntity);
    }

    public List<ContactLaboratoireDTO> getAllContacts() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ContactLaboratoireDTO getContactById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("ContactLaboratoire not found"));
    }

    public void deleteContact(Long id) {
        repository.deleteById(id);
    }

    private ContactLaboratoire mapToEntity(ContactLaboratoireDTO dto) {
        ContactLaboratoire entity = new ContactLaboratoire();
        entity.setId(dto.getId());
        entity.setFkIdLaboratoire(dto.getFkIdLaboratoire());
        entity.setFkIdAdresse(dto.getFkIdAdresse());
        entity.setNumTel(dto.getNumTel());
        entity.setFax(dto.getFax());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    private ContactLaboratoireDTO mapToDTO(ContactLaboratoire entity) {
        ContactLaboratoireDTO dto = new ContactLaboratoireDTO();
        dto.setId(entity.getId());
        dto.setFkIdLaboratoire(entity.getFkIdLaboratoire());
        dto.setFkIdAdresse(entity.getFkIdAdresse());
        dto.setNumTel(entity.getNumTel());
        dto.setFax(entity.getFax());
        dto.setEmail(entity.getEmail());
        return dto;
    }
}
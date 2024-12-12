package com.labo.laboratoire.controllers;

import com.labo.laboratoire.dtos.ContactLaboratoireDTO;
import com.labo.laboratoire.services.ContactLaboratoireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contactLaboratoire")
public class ContactLaboratoireController {

    @Autowired
    private ContactLaboratoireService service;

    @PostMapping
    public ContactLaboratoireDTO createContact(@RequestBody ContactLaboratoireDTO dto) {
        return service.saveContact(dto);
    }

    @GetMapping
    public List<ContactLaboratoireDTO> getAllContacts() {
        return service.getAllContacts();
    }

    @GetMapping("/{id}")
    public ContactLaboratoireDTO getContactById(@PathVariable Long id) {
        return service.getContactById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        service.deleteContact(id);
    }
}
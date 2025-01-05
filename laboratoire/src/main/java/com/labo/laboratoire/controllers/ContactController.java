package com.labo.laboratoire.controllers;

import com.labo.laboratoire.dtos.ContactDTO;
import com.labo.laboratoire.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {

    @Autowired
    private ContactService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public ContactDTO createContact(@RequestBody ContactDTO dto) {
        return service.saveContact(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public List<ContactDTO> getAllContacts() {
        return service.getAllContacts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public ContactDTO getContactById(@PathVariable Long id) {
        return service.getContactById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public void deleteContact(@PathVariable Long id) {
        service.deleteContact(id);
    }
}
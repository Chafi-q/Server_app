package com.labo.laboratoire.controllers;

import com.labo.laboratoire.dtos.ContactDTO;
import com.labo.laboratoire.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {

    @Autowired
    private ContactService service;

    @PostMapping
    public ContactDTO createContact(@RequestBody ContactDTO dto) {
        return service.saveContact(dto);
    }

    @GetMapping
    public List<ContactDTO> getAllContacts() {
        return service.getAllContacts();
    }

    @GetMapping("/{id}")
    public ContactDTO getContactById(@PathVariable Long id) {
        return service.getContactById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        service.deleteContact(id);
    }
}
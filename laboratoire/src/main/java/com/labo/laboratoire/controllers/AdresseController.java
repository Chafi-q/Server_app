package com.labo.laboratoire.controllers;

import com.labo.laboratoire.dtos.AdresseDTO;
import com.labo.laboratoire.services.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/adresses")
public class AdresseController {

    @Autowired
    private AdresseService service;

    @PostMapping
    public AdresseDTO createAdresse(@RequestBody AdresseDTO dto) {
        return service.saveAdresse(dto);
    }

    @GetMapping
    public List<AdresseDTO> getAllAdresses() {
        return service.getAllAdresses();
    }

    @GetMapping("/{id}")
    public AdresseDTO getAdresseById(@PathVariable Long id) {
        return service.getAdresseById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAdresse(@PathVariable Long id) {
        service.deleteAdresse(id);
    }
}
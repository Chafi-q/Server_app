package com.labo.patient.controllers;

import com.labo.patient.dtos.DossierDTO;
import com.labo.patient.services.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dossiers")
public class DossierController {

    @Autowired
    private DossierService service;

    @PostMapping
    public DossierDTO createDossier(@RequestBody DossierDTO dto) {
        return service.saveDossier(dto);
    }

    @GetMapping
    public List<DossierDTO> getAllDossiers() {
        return service.getAllDossiers();
    }

    @GetMapping("/{id}")
    public DossierDTO getDossierById(@PathVariable Long id) {
        return service.getDossierById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDossier(@PathVariable Long id) {
        service.deleteDossier(id);
    }
}
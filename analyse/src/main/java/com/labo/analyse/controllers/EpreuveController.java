package com.labo.analyse.controllers;

import com.labo.analyse.dtos.EpreuveDTO;
import com.labo.analyse.services.EpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/epreuves")
public class EpreuveController {

    @Autowired
    private EpreuveService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public EpreuveDTO createEpreuve(@RequestBody EpreuveDTO dto) {
        return service.saveEpreuve(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public List<EpreuveDTO> getAllEpreuves() {
        return service.getAllEpreuves();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public EpreuveDTO getEpreuveById(@PathVariable Long id) {
        return service.getEpreuveById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public void deleteEpreuve(@PathVariable Long id) {
        service.deleteEpreuve(id);
    }
}
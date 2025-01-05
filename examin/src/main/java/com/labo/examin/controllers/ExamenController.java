package com.labo.examin.controllers;

import com.labo.examin.dtos.ExamenDTO;
import com.labo.examin.services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/examens")
public class ExamenController {

    @Autowired
    private ExamenService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public ExamenDTO createExamen(@RequestBody ExamenDTO dto) {
        return service.saveExamen(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public List<ExamenDTO> getAllExamens() {
        return service.getAllExamens();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public ExamenDTO getExamenById(@PathVariable Long id) {
        return service.getExamenById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public void deleteExamen(@PathVariable Long id) {
        service.deleteExamen(id);
    }
}
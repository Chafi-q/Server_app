package com.labo.analyse.controllers;

import com.labo.analyse.dtos.AnalyseDTO;
import com.labo.analyse.services.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/analyses")
public class AnalyseController {

    @Autowired
    private AnalyseService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public AnalyseDTO createAnalyse(@RequestBody AnalyseDTO dto) {
        return service.saveAnalyse(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public List<AnalyseDTO> getAllAnalyses() {
        return service.getAllAnalyses();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE', 'USER')")
    public AnalyseDTO getAnalyseById(@PathVariable Long id) {
        return service.getAnalyseById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public void deleteAnalyse(@PathVariable Long id) {
        service.deleteAnalyse(id);
    }
}
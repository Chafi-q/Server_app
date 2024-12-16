package com.labo.analyse.controllers;

import com.labo.analyse.dtos.EpreuveDTO;
import com.labo.analyse.services.EpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/epreuves")
public class EpreuveController {

    @Autowired
    private EpreuveService service;

    @PostMapping
    public EpreuveDTO createEpreuve(@RequestBody EpreuveDTO dto) {
        return service.saveEpreuve(dto);
    }

    @GetMapping
    public List<EpreuveDTO> getAllEpreuves() {
        return service.getAllEpreuves();
    }

    @GetMapping("/{id}")
    public EpreuveDTO getEpreuveById(@PathVariable Long id) {
        return service.getEpreuveById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEpreuve(@PathVariable Long id) {
        service.deleteEpreuve(id);
    }
}
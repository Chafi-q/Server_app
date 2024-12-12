package com.labo.test.controllers;

import com.labo.test.dtos.TestAnalyseDTO;
import com.labo.test.services.TestAnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/testAnalyse")
public class TestAnalyseController {

    @Autowired
    private TestAnalyseService service;

    @PostMapping
    public TestAnalyseDTO createTestAnalyse(@RequestBody TestAnalyseDTO dto) {
        return service.saveTestAnalyse(dto);
    }

    @GetMapping
    public List<TestAnalyseDTO> getAllTestAnalyses() {
        return service.getAllTestAnalyses();
    }

    @GetMapping("/{id}")
    public TestAnalyseDTO getTestAnalyseById(@PathVariable Long id) {
        return service.getTestAnalyseById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTestAnalyse(@PathVariable Long id) {
        service.deleteTestAnalyse(id);
    }
}
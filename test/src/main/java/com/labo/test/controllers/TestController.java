package com.labo.test.controllers;

import com.labo.test.dtos.TestDTO;
import com.labo.test.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tests")
public class TestController {

    @Autowired
    private TestService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public TestDTO createTestAnalyse(@RequestBody TestDTO dto) {
        return service.saveTestAnalyse(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public List<TestDTO> getAllTestAnalyses() {
        return service.getAllTestAnalyses();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public TestDTO getTestAnalyseById(@PathVariable Long id) {
        return service.getTestAnalyseById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public void deleteTestAnalyse(@PathVariable Long id) {
        service.deleteTestAnalyse(id);
    }
}
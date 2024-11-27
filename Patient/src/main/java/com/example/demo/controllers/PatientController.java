package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.PatientService;
import com.example.demo.entities.PatientEntity;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<PatientEntity> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientEntity> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PatientEntity createPatient(@RequestBody PatientEntity patient) {
        return patientService.savePatient(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientEntity> updatePatient(@PathVariable Long id, @RequestBody PatientEntity updatedPatient) {
        return patientService.getPatientById(id)
                .map(existingPatient -> {
                    updatedPatient.setId(id);
                    return ResponseEntity.ok(patientService.savePatient(updatedPatient));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        if (patientService.getPatientById(id).isPresent()) {
            patientService.deletePatient(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

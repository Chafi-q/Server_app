package com.labo.patient.controllers;

import com.labo.patient.dtos.DossierDTO;
import com.labo.patient.entities.Dossier;
import com.labo.patient.services.DossierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dossiers")
@RequiredArgsConstructor
public class DossierController {

    private final DossierService dossierService;

    @PostMapping("/patient/{patientId}")
    public ResponseEntity<DossierDTO> createDossierForPatient(
            @PathVariable Long patientId,
            @RequestBody DossierDTO dossierDTO) {
        Dossier dossier = dossierService.createDossier(patientId, dossierDTO.getDate());
        DossierDTO responseDTO = new DossierDTO(dossier.getNumDossier(), dossier.getDate(), dossier.getPatient().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<DossierDTO>> getAllDossiers() {
        List<DossierDTO> dossiers = dossierService.getAllDossiers().stream()
                .map(dossier -> new DossierDTO(dossier.getNumDossier(), dossier.getDate(), dossier.getPatient().getId()))
                .toList();
        return ResponseEntity.ok(dossiers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DossierDTO> getDossierById(@PathVariable Long id) {
        Dossier dossier = dossierService.getDossierById(id);
        DossierDTO responseDTO = new DossierDTO(dossier.getNumDossier(), dossier.getDate(), dossier.getPatient().getId());
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<DossierDTO> getDossierByPatientId(@PathVariable Long patientId) {
        Dossier dossier = dossierService.getDossierByPatientId(patientId);
        DossierDTO responseDTO = new DossierDTO(dossier.getNumDossier(), dossier.getDate(), dossier.getPatient().getId());
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDossier(@PathVariable Long id) {
        dossierService.deleteDossier(id);
        return ResponseEntity.noContent().build();
    }
}

package com.labo.laboratoire.controllers;

import com.labo.laboratoire.entities.Laboratoire;
import com.labo.laboratoire.services.LaboratoireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/laboratoires")
public class LaboratoireController {

    @Autowired
    private LaboratoireService laboratoireService;

   
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public ResponseEntity<Laboratoire> createLaboratoire(@RequestBody Laboratoire laboratoire) {
        Laboratoire newLaboratoire = laboratoireService.createLaboratoire(laboratoire);
        return ResponseEntity.ok(newLaboratoire);
    }

   
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public ResponseEntity<Laboratoire> getLaboratoireById(@PathVariable Long id) {
        return laboratoireService.getLaboratoireById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public ResponseEntity<Laboratoire> updateLaboratoire(@PathVariable Long id, @RequestBody Laboratoire laboratoireDetails) {
        Laboratoire updatedLaboratoire = laboratoireService.updateLaboratoire(id, laboratoireDetails);
        return ResponseEntity.ok(updatedLaboratoire);
    }

    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public ResponseEntity<Void> deleteLaboratoire(@PathVariable Long id) {
        laboratoireService.deleteLaboratoire(id);
        return ResponseEntity.noContent().build();
    }

   
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public ResponseEntity<List<Laboratoire>> getAllLaboratoires() {
        return ResponseEntity.ok(laboratoireService.getAllLaboratoires());
    }
}

package com.labo.utilisateur.controllers;

import com.labo.utilisateur.dtos.UtilisateurDTO;
import com.labo.utilisateur.entities.Utilisateur;
import com.labo.utilisateur.services.UtilisateurServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {
    private final UtilisateurServiceImpl utilisateurService;

    @PostMapping
    @ResponseStatus
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) {
        return ResponseEntity.ok(utilisateurService.createUtilisateur(utilisateurDTO));
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.getAllUtilisateurs());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Integer id) {
        return ResponseEntity.ok(utilisateurService.getUtilisateurById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Integer id,
                                                         @Valid @RequestBody UtilisateurDTO utilisateurDTO) {
        return ResponseEntity.ok(utilisateurService.updateUtilisateur(id, utilisateurDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Integer id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }

}

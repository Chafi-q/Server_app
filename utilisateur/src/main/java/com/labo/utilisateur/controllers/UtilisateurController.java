package com.labo.utilisateur.controllers;

import com.labo.utilisateur.entities.Utilisateur;
import com.labo.utilisateur.services.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    @PostMapping
    @ResponseStatus
    public void save(@RequestBody Utilisateur utilisateur) {
        utilisateurService.saveUtilisateur(utilisateur);
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> findAll() {
        return ResponseEntity.ok(utilisateurService.findAllUtilisateurs());
    }

}

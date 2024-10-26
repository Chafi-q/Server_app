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
    public void saveUser(@RequestBody Utilisateur utilisateur) {
        utilisateurService.saveUser(utilisateur);
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> findAllUsers() {
        return ResponseEntity.ok(utilisateurService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> findUserById (@PathVariable Integer id) {
        return ResponseEntity.ok(utilisateurService.findUserById(id));
    }

    @PutMapping
    public ResponseEntity<Utilisateur> updateUser(@RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(utilisateurService.updateUser(utilisateur));
    }

    @DeleteMapping
    public void deleteUser(@RequestBody Utilisateur utilisateur) {
        utilisateurService.deleteUser(utilisateur);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        utilisateurService.deleteUserbyId(id);
    }
}

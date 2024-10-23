package com.labo.utilisateur.services;

import com.labo.utilisateur.entities.Utilisateur;
import com.labo.utilisateur.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepo;
    public void saveUtilisateur(Utilisateur utilisateur) {
        utilisateurRepo.save(utilisateur);
    }

    public List<Utilisateur> findAllUtilisateurs() {
        return utilisateurRepo.findAll();
    }



}

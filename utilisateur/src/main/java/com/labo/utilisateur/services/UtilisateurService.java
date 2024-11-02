package com.labo.utilisateur.services;

import com.labo.utilisateur.dtos.UtilisateurDTO;
import com.labo.utilisateur.entities.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    Utilisateur createUtilisateur(UtilisateurDTO utilisateurDTO);
    Utilisateur getUtilisateurById(Integer id);
    List<Utilisateur> getAllUtilisateurs();
    Utilisateur updateUtilisateur(Integer id, UtilisateurDTO utilisateurDTO);
    void deleteUtilisateur(Integer id);
}

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
    public void saveUser(Utilisateur utilisateur) {
        utilisateurRepo.save(utilisateur);
    }

    public List<Utilisateur> findAllUsers() {
        return utilisateurRepo.findAll();
    }
    public Utilisateur findUserById(Integer id){
        return utilisateurRepo.getReferenceById(id);
    }
    public Utilisateur updateUser(Utilisateur utilisateur) {
        Utilisateur user = new Utilisateur();
        user.setEmail(utilisateur.getEmail());
        user.setNomComplet(utilisateur.getNomComplet());
        user.setProfession(utilisateur.getProfession());
        user.setNumTel(utilisateur.getNumTel());
        user.setSignature(utilisateur.getSignature());
        user.setRole(utilisateur.getRole());
        user.setIdLabo(utilisateur.getIdLabo());

        return user;
    }

    public void deleteUser (Utilisateur utilisateur) {
        utilisateurRepo.delete(utilisateur);
    }
    public void deleteUserbyId(Integer id) {
        utilisateurRepo.deleteById(id);
    }


}

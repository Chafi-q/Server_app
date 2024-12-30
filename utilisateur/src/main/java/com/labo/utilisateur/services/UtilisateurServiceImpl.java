package com.labo.utilisateur.services;

import com.labo.utilisateur.FeignClient.LaboratoireClient;
import com.labo.utilisateur.dtos.LaboratoireDTO;
import com.labo.utilisateur.dtos.UtilisateurDTO;
import com.labo.utilisateur.entities.Utilisateur;
import com.labo.utilisateur.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
//@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;
    private final LaboratoireClient laboratoireClient;

    public UtilisateurServiceImpl(LaboratoireClient laboratoireClient) {
        this.laboratoireClient = laboratoireClient;
    }

    public LaboratoireDTO getLaboratoireDetails(Long fkIdLaboratoire) {
        return laboratoireClient.getLaboratoireById(fkIdLaboratoire);
    }

    @Override
    public Utilisateur createUtilisateur(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = mapToEntity(utilisateurDTO);
        log.info("Creating user with email: {}", utilisateur.getEmail());
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur getUtilisateurById(Integer id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }


    @Override
    public Utilisateur updateUtilisateur(Integer id, UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = getUtilisateurById(id);
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setNomComplet(utilisateurDTO.getNomComplet());
        utilisateur.setProfession(utilisateurDTO.getProfession());
        utilisateur.setNumTel(utilisateurDTO.getNumTel());
        utilisateur.setSignature(utilisateurDTO.getSignature());
        utilisateur.setRole(utilisateurDTO.getRole());
        utilisateur.setIdLabo(utilisateurDTO.getIdLabo());
        log.info("Updating user with id: {}", id);
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public void deleteUtilisateur(Integer id) {
        log.info("Deleting user with id: {}", id);
        utilisateurRepository.deleteById(id);
    }

    private Utilisateur mapToEntity(UtilisateurDTO dto) {
        return new Utilisateur(
                dto.getId(),
                dto.getEmail(),
                dto.getNomComplet(),
                dto.getProfession(),
                dto.getNumTel(),
                dto.getSignature(),
                dto.getRole(),
                dto.getIdLabo()
        );
    }

}

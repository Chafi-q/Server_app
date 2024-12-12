package com.labo.laboratoire.services;

import com.labo.laboratoire.Repositories.AdresseRepository;
import com.labo.laboratoire.dtos.AdresseDTO;
import com.labo.laboratoire.entities.Adresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdresseService {

    @Autowired
    private AdresseRepository repository;

    public AdresseDTO saveAdresse(AdresseDTO dto) {
        Adresse entity = mapToEntity(dto);
        Adresse savedEntity = repository.save(entity);
        return mapToDTO(savedEntity);
    }

    public List<AdresseDTO> getAllAdresses() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public AdresseDTO getAdresseById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Adresse not found"));
    }

    public void deleteAdresse(Long id) {
        repository.deleteById(id);
    }

    private Adresse mapToEntity(AdresseDTO dto) {
        Adresse entity = new Adresse();
        entity.setId(dto.getId());
        entity.setNumVoie(dto.getNumVoie());
        entity.setNomVoie(dto.getNomVoie());
        entity.setCodePostal(dto.getCodePostal());
        entity.setVille(dto.getVille());
        entity.setCommune(dto.getCommune());
        return entity;
    }

    private AdresseDTO mapToDTO(Adresse entity) {
        AdresseDTO dto = new AdresseDTO();
        dto.setId(entity.getId());
        dto.setNumVoie(entity.getNumVoie());
        dto.setNomVoie(entity.getNomVoie());
        dto.setCodePostal(entity.getCodePostal());
        dto.setVille(entity.getVille());
        dto.setCommune(entity.getCommune());
        return dto;
    }
}
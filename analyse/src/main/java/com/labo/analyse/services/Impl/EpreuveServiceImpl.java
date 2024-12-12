package com.labo.analyse.services.Impl;

import com.labo.analyse.dtos.EpreuveDTO;
import com.labo.analyse.entities.Epreuve;
import com.labo.analyse.repositories.EpreuveRepository;
import com.labo.analyse.services.EpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EpreuveServiceImpl implements EpreuveService {

    @Autowired
    private EpreuveRepository repository;

    public EpreuveDTO saveEpreuve(EpreuveDTO dto) {
        Epreuve entity = mapToEntity(dto);
        Epreuve savedEntity = repository.save(entity);
        return mapToDTO(savedEntity);
    }

    public List<EpreuveDTO> getAllEpreuves() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public EpreuveDTO getEpreuveById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Epreuve not found"));
    }

    public void deleteEpreuve(Long id) {
        repository.deleteById(id);
    }

    private Epreuve mapToEntity(EpreuveDTO dto) {
        Epreuve entity = new Epreuve();
        entity.setId(dto.getId());
        entity.setFkIdAnalyse(dto.getFkIdAnalyse());
        entity.setNom(dto.getNom());
        return entity;
    }

    private EpreuveDTO mapToDTO(Epreuve entity) {
        EpreuveDTO dto = new EpreuveDTO();
        dto.setId(entity.getId());
        dto.setFkIdAnalyse(entity.getFkIdAnalyse());
        dto.setNom(entity.getNom());
        return dto;
    }
}
package com.labo.analyse.services.Impl;


import com.labo.analyse.FeignClient.LaboratoireClient;
import com.labo.analyse.dtos.AnalyseDTO;
import com.labo.analyse.dtos.LaboratoireDTO;
import com.labo.analyse.entities.Analyse;
import com.labo.analyse.repositories.AnalyseRepository;
import com.labo.analyse.services.AnalyseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class AnalyseServiceImpl implements AnalyseService {

    private  LaboratoireClient laboratoireClient;

    public AnalyseServiceImpl(LaboratoireClient laboratoireClient) {
        this.laboratoireClient = laboratoireClient;
    }

    public LaboratoireDTO getLaboratoireDetails(Long fkIdLaboratoire) {
        return laboratoireClient.getLaboratoireById(fkIdLaboratoire);
    }


    @Autowired
    private AnalyseRepository repository;

    public AnalyseDTO saveAnalyse(AnalyseDTO dto) {
        Analyse entity = mapToEntity(dto);
        Analyse savedEntity = repository.save(entity);
        return mapToDTO(savedEntity);
    }

    public List<AnalyseDTO> getAllAnalyses() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public AnalyseDTO getAnalyseById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Analyse not found"));
    }

    public void deleteAnalyse(Long id) {
        repository.deleteById(id);
    }

    private Analyse mapToEntity(AnalyseDTO dto) {
        Analyse entity = new Analyse();
        entity.setId(dto.getId());
        entity.setFkIdLaboratoire(dto.getFkIdLaboratoire());
        entity.setNom(dto.getNom());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    private AnalyseDTO mapToDTO(Analyse entity) {
        AnalyseDTO dto = new AnalyseDTO();
        dto.setId(entity.getId());
        dto.setFkIdLaboratoire(entity.getFkIdLaboratoire());
        dto.setNom(entity.getNom());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
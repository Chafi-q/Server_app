package com.labo.examin.services.Impl;

import com.labo.examin.FeingClient.TestAnalyseClient;
import com.labo.examin.dtos.ExamenDTO;
import com.labo.examin.dtos.TestAnalyseDTO;
import com.labo.examin.entities.Examen;
import com.labo.examin.repositories.ExamenRepository;
import com.labo.examin.services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamenServiceImpl implements ExamenService {

    @Autowired
    private ExamenRepository repository;
    @Autowired
    private TestAnalyseClient testAnalyseClient;

    public ExamenServiceImpl(TestAnalyseClient testAnalyseClient) {
        this.testAnalyseClient = testAnalyseClient;
    }
  //  public TestAnalyseDTO getTestAnalyseDetails(Long fkIdTestAnalyse) {
   //     return testAnalyseClient.getTestAnalyseById(fkIdTestAnalyse);
   // }

    public ExamenDTO saveExamen(ExamenDTO dto) {
        Examen entity = mapToEntity(dto);
        Examen savedEntity = repository.save(entity);
        return mapToDTO(savedEntity);
    }

    public List<ExamenDTO> getAllExamens() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ExamenDTO getExamenById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Examen not found"));
    }

    public void deleteExamen(Long id) {
        repository.deleteById(id);
    }

    private Examen mapToEntity(ExamenDTO dto) {
        Examen entity = new Examen();
        entity.setId(dto.getId());
        entity.setFkNumDossier(dto.getFkNumDossier());
        entity.setFkIdEpreuve(dto.getFkIdEpreuve());
        entity.setFkIdTestAnalyse(dto.getFkIdTestAnalyse());
        entity.setResultat(dto.getResultat());
        return entity;
    }

    private ExamenDTO mapToDTO(Examen entity) {
        ExamenDTO dto = new ExamenDTO();
        dto.setId(entity.getId());
        dto.setFkNumDossier(entity.getFkNumDossier());
        dto.setFkIdEpreuve(entity.getFkIdEpreuve());
        dto.setFkIdTestAnalyse(entity.getFkIdTestAnalyse());
        dto.setResultat(entity.getResultat());
        return dto;
    }
}
package com.labo.test.services.Impl;

import com.labo.test.dtos.TestAnalyseDTO;
import com.labo.test.entities.TestAnalyse;
import com.labo.test.repositories.TestAnalyseRepository;
import com.labo.test.services.TestAnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestAnalyseServiceImpl implements TestAnalyseService {

    @Autowired
    private TestAnalyseRepository repository;

    public TestAnalyseDTO saveTestAnalyse(TestAnalyseDTO dto) {
        TestAnalyse entity = mapToEntity(dto);
        TestAnalyse savedEntity = repository.save(entity);
        return mapToDTO(savedEntity);
    }

    public List<TestAnalyseDTO> getAllTestAnalyses() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TestAnalyseDTO getTestAnalyseById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("TestAnalyse not found"));
    }

    public void deleteTestAnalyse(Long id) {
        repository.deleteById(id);
    }

    private TestAnalyse mapToEntity(TestAnalyseDTO dto) {
        TestAnalyse entity = new TestAnalyse();
        entity.setId(dto.getId());
        entity.setFkIdAnalyse(dto.getFkIdAnalyse());
        entity.setNomTest(dto.getNomTest());
        entity.setSousEpreuve(dto.getSousEpreuve());
        entity.setIntervalMinDeReference(dto.getIntervalMinDeReference());
        entity.setIntervalMaxDeReference(dto.getIntervalMaxDeReference());
        entity.setUniteDeReference(dto.getUniteDeReference());
        entity.setDetails(dto.getDetails());
        return entity;
    }

    private TestAnalyseDTO mapToDTO(TestAnalyse entity) {
        TestAnalyseDTO dto = new TestAnalyseDTO();
        dto.setId(entity.getId());
        dto.setFkIdAnalyse(entity.getFkIdAnalyse());
        dto.setNomTest(entity.getNomTest());
        dto.setSousEpreuve(entity.getSousEpreuve());
        dto.setIntervalMinDeReference(entity.getIntervalMinDeReference());
        dto.setIntervalMaxDeReference(entity.getIntervalMaxDeReference());
        dto.setUniteDeReference(entity.getUniteDeReference());
        dto.setDetails(entity.getDetails());
        return dto;
    }
}
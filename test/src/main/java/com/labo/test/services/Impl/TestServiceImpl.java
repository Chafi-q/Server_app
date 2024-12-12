package com.labo.test.services.Impl;

import com.labo.test.dtos.TestDTO;
import com.labo.test.entities.Test;
import com.labo.test.repositories.TestRepository;
import com.labo.test.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository repository;

    public TestDTO saveTestAnalyse(TestDTO dto) {
        Test entity = mapToEntity(dto);
        Test savedEntity = repository.save(entity);
        return mapToDTO(savedEntity);
    }

    public List<TestDTO> getAllTestAnalyses() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TestDTO getTestAnalyseById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("TestAnalyse not found"));
    }

    public void deleteTestAnalyse(Long id) {
        repository.deleteById(id);
    }

    private Test mapToEntity(TestDTO dto) {
        Test entity = new Test();
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

    private TestDTO mapToDTO(Test entity) {
        TestDTO dto = new TestDTO();
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
package com.labo.test.services;

import com.labo.test.dtos.TestAnalyseDTO;

import java.util.List;

public interface TestAnalyseService {

    TestAnalyseDTO saveTestAnalyse(TestAnalyseDTO dto);

    List<TestAnalyseDTO> getAllTestAnalyses();

    TestAnalyseDTO getTestAnalyseById(Long id);

    void deleteTestAnalyse(Long id);
}
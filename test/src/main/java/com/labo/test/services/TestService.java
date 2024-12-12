package com.labo.test.services;

import com.labo.test.dtos.TestDTO;

import java.util.List;

public interface TestService {

    TestDTO saveTestAnalyse(TestDTO dto);

    List<TestDTO> getAllTestAnalyses();

    TestDTO getTestAnalyseById(Long id);

    void deleteTestAnalyse(Long id);
}
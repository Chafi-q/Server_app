package com.labo.analyse.services;

import com.labo.analyse.dtos.AnalyseDTO;

import java.util.List;

public interface AnalyseService {

    AnalyseDTO saveAnalyse(AnalyseDTO dto);

    List<AnalyseDTO> getAllAnalyses();

    AnalyseDTO getAnalyseById(Long id);

    void deleteAnalyse(Long id);
}
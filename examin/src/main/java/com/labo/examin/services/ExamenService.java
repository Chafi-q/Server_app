package com.labo.examin.services;

import com.labo.examin.dtos.ExamenDTO;

import java.util.List;

public interface ExamenService {

    ExamenDTO saveExamen(ExamenDTO dto);

    List<ExamenDTO> getAllExamens();

    ExamenDTO getExamenById(Long id);

    void deleteExamen(Long id);
}
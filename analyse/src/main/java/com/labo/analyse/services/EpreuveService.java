package com.labo.analyse.services;

import com.labo.analyse.dtos.EpreuveDTO;

import java.util.List;

public interface EpreuveService {

    EpreuveDTO saveEpreuve(EpreuveDTO dto);

    List<EpreuveDTO> getAllEpreuves();

    EpreuveDTO getEpreuveById(Long id);

    void deleteEpreuve(Long id);
}
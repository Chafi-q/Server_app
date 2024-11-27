package com.example.demo.mappers;

import com.example.demo.dtos.PatientDTO;
import com.example.demo.entities.PatientEntity;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public PatientDTO toDTO(PatientEntity entity) {
        PatientDTO dto = new PatientDTO();
        dto.setId(entity.getId());
        dto.setNomComplet(entity.getNomComplet());
        dto.setDateNaissance(entity.getDateNaissance().toString());
        dto.setLieuDeNaissance(entity.getLieuDeNaissance());
        dto.setSexe(entity.getSexe());
        dto.setTypePieceIdentite(entity.getTypePieceIdentite());
        dto.setNumPieceIdentite(entity.getNumPieceIdentite());
        dto.setAdresse(entity.getAdresse());
        dto.setNumTel(entity.getNumTel());
        dto.setEmail(entity.getEmail());
        dto.setVisiblePour(entity.getVisiblePour());
        return dto;
    }

    public PatientEntity toEntity(PatientDTO dto) {
        PatientEntity entity = new PatientEntity();
        entity.setId(dto.getId());
        entity.setNomComplet(dto.getNomComplet());
        entity.setDateNaissance(java.sql.Date.valueOf(dto.getDateNaissance()));
        entity.setLieuDeNaissance(dto.getLieuDeNaissance());
        entity.setSexe(dto.getSexe());
        entity.setTypePieceIdentite(dto.getTypePieceIdentite());
        entity.setNumPieceIdentite(dto.getNumPieceIdentite());
        entity.setAdresse(dto.getAdresse());
        entity.setNumTel(dto.getNumTel());
        entity.setEmail(dto.getEmail());
        entity.setVisiblePour(dto.getVisiblePour());
        return entity;
    }
}


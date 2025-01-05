package com.labo.patient.repositories;

import com.labo.patient.entities.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {
    Optional<Dossier> findByPatientId(Long patientId);
}
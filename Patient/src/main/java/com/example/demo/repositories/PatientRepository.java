package com.example.demo.repositories;

import com.example.demo.entities.PatientEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
}

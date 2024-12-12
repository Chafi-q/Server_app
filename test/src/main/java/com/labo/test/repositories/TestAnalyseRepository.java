package com.labo.test.repositories;

import com.labo.test.entities.TestAnalyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestAnalyseRepository extends JpaRepository<TestAnalyse, Long> {
}
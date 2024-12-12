package com.labo.test;

import com.labo.test.dtos.TestAnalyseDTO;
import com.labo.test.entities.TestAnalyse;
import com.labo.test.repositories.TestAnalyseRepository;
import com.labo.test.services.TestAnalyseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestAnalyseServiceTest {

    @InjectMocks
    private TestAnalyseService service;

    @Mock
    private TestAnalyseRepository repository;

    public TestAnalyseServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveTestAnalyse() {
        TestAnalyseDTO dto = new TestAnalyseDTO();
        dto.setFkIdAnalyse(1L);
        dto.setNomTest("Test 1");
        dto.setSousEpreuve("SousEpreuve 1");
        dto.setIntervalMinDeReference("10");
        dto.setIntervalMaxDeReference("20");
        dto.setUniteDeReference("mg/dL");
        dto.setDetails("Details 1");

        TestAnalyse entity = new TestAnalyse();
        entity.setFkIdAnalyse(1L);
        entity.setNomTest("Test 1");
        entity.setSousEpreuve("SousEpreuve 1");
        entity.setIntervalMinDeReference("10");
        entity.setIntervalMaxDeReference("20");
        entity.setUniteDeReference("mg/dL");
        entity.setDetails("Details 1");

        when(repository.save(any(TestAnalyse.class))).thenReturn(entity);

        TestAnalyseDTO result = service.saveTestAnalyse(dto);

        assertEquals(dto.getNomTest(), result.getNomTest());
        verify(repository, times(1)).save(any(TestAnalyse.class));
    }

    @Test
    public void testGetTestAnalyseById() {
        TestAnalyse entity = new TestAnalyse();
        entity.setId(1L);
        entity.setFkIdAnalyse(1L);
        entity.setNomTest("Test 1");
        entity.setSousEpreuve("SousEpreuve 1");
        entity.setIntervalMinDeReference("10");
        entity.setIntervalMaxDeReference("20");
        entity.setUniteDeReference("mg/dL");
        entity.setDetails("Details 1");

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        TestAnalyseDTO result = service.getTestAnalyseById(1L);

        assertEquals(entity.getNomTest(), result.getNomTest());
        verify(repository, times(1)).findById(1L);
    }
}

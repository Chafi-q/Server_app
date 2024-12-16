package com.labo.test;

import com.labo.test.dtos.TestDTO;
import com.labo.test.entities.Test;
import com.labo.test.repositories.TestRepository;
import com.labo.test.services.TestService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestAnalyseServiceTest {

    @InjectMocks
    private TestService service;

    @Mock
    private TestRepository repository;

    public TestAnalyseServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @org.junit.jupiter.api.Test
    public void testSaveTestAnalyse() {
        TestDTO dto = new TestDTO();
        dto.setFkIdAnalyse(1L);
        dto.setNomTest("Test 1");
        dto.setSousEpreuve("SousEpreuve 1");
        dto.setIntervalMinDeReference("10");
        dto.setIntervalMaxDeReference("20");
        dto.setUniteDeReference("mg/dL");
        dto.setDetails("Details 1");

        Test entity = new Test();
        entity.setFkIdAnalyse(1L);
        entity.setNomTest("Test 1");
        entity.setSousEpreuve("SousEpreuve 1");
        entity.setIntervalMinDeReference("10");
        entity.setIntervalMaxDeReference("20");
        entity.setUniteDeReference("mg/dL");
        entity.setDetails("Details 1");

        when(repository.save(any(Test.class))).thenReturn(entity);

        TestDTO result = service.saveTestAnalyse(dto);

        assertEquals(dto.getNomTest(), result.getNomTest());
        verify(repository, times(1)).save(any(Test.class));
    }

    @org.junit.jupiter.api.Test
    public void testGetTestAnalyseById() {
        Test entity = new Test();
        entity.setId(1L);
        entity.setFkIdAnalyse(1L);
        entity.setNomTest("Test 1");
        entity.setSousEpreuve("SousEpreuve 1");
        entity.setIntervalMinDeReference("10");
        entity.setIntervalMaxDeReference("20");
        entity.setUniteDeReference("mg/dL");
        entity.setDetails("Details 1");

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        TestDTO result = service.getTestAnalyseById(1L);

        assertEquals(entity.getNomTest(), result.getNomTest());
        verify(repository, times(1)).findById(1L);
    }
}

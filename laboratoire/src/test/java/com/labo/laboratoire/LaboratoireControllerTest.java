package com.labo.laboratoire;

import entities.Laboratoire;
import services.LaboratoireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import controllers.LaboratoireController;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LaboratoireController.class)
class LaboratoireControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LaboratoireService laboratoireService;

    @InjectMocks
    private LaboratoireController laboratoireController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(laboratoireController).build();
    }

    @Test
    void testGetAllLaboratoires() throws Exception {
        when(laboratoireService.getAllLaboratoires()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/laboratoires"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void testCreateLaboratoire() throws Exception {
        Laboratoire laboratoire = new Laboratoire("Laboratoire A", "logoA.png", "NRC123", true, LocalDate.now());
        when(laboratoireService.createLaboratoire(laboratoire)).thenReturn(laboratoire);

        mockMvc.perform(post("/api/laboratoires")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nom\":\"Laboratoire A\",\"nrc\":\"NRC123\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"nom\":\"Laboratoire A\",\"nrc\":\"NRC123\"}"));
    }
}

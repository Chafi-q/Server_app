package com.labo.utilisateur;

import com.labo.utilisateur.controllers.UtilisateurController;
import com.labo.utilisateur.dtos.UtilisateurDTO;
import com.labo.utilisateur.entities.Utilisateur;
import com.labo.utilisateur.services.UtilisateurServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UtilisateurControllerTest {

    @Mock
    private UtilisateurServiceImpl utilisateurService;

    @InjectMocks
    private UtilisateurController utilisateurController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUtilisateur() {
        UtilisateurDTO dto = UtilisateurDTO.builder()
                .email("test@example.com")
                .nomComplet("Test User")
                .build();

        Utilisateur utilisateur = Utilisateur.builder()
                .email("test@example.com")
                .nomComplet("Test User")
                .build();

        when(utilisateurService.createUtilisateur(dto)).thenReturn(utilisateur);

        ResponseEntity<Utilisateur> response = utilisateurController.createUtilisateur(dto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(utilisateur.getEmail(), response.getBody().getEmail());

        verify(utilisateurService, times(1)).createUtilisateur(dto);
    }

    @Test
    void testGetAllUtilisateurs() {
        Utilisateur utilisateur1 = Utilisateur.builder().id(1).email("test1@example.com").build();
        Utilisateur utilisateur2 = Utilisateur.builder().id(2).email("test2@example.com").build();

        when(utilisateurService.getAllUtilisateurs()).thenReturn(Arrays.asList(utilisateur1, utilisateur2));

        ResponseEntity<List<Utilisateur>> response = utilisateurController.getAllUtilisateurs();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());

        verify(utilisateurService, times(1)).getAllUtilisateurs();
    }

    @Test
    void testGetUtilisateurById() {
        Utilisateur utilisateur = Utilisateur.builder().id(1).email("test@example.com").build();

        when(utilisateurService.getUtilisateurById(1)).thenReturn(utilisateur);

        ResponseEntity<Utilisateur> response = utilisateurController.getUtilisateurById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(utilisateur.getId(), response.getBody().getId());

        verify(utilisateurService, times(1)).getUtilisateurById(1);
    }

    @Test
    void testUpdateUtilisateur() {
        UtilisateurDTO dto = UtilisateurDTO.builder().email("updated@example.com").build();
        Utilisateur utilisateur = Utilisateur.builder().id(1).email("updated@example.com").build();

        when(utilisateurService.updateUtilisateur(1, dto)).thenReturn(utilisateur);

        ResponseEntity<Utilisateur> response = utilisateurController.updateUtilisateur(1, dto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(utilisateur.getEmail(), response.getBody().getEmail());

        verify(utilisateurService, times(1)).updateUtilisateur(1, dto);
    }

    @Test
    void testDeleteUtilisateur() {
        doNothing().when(utilisateurService).deleteUtilisateur(1);

        ResponseEntity<Void> response = utilisateurController.deleteUtilisateur(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(utilisateurService, times(1)).deleteUtilisateur(1);
    }
}
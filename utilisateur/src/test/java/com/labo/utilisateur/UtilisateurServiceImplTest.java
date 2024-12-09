package com.labo.utilisateur;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.labo.utilisateur.dtos.UtilisateurDTO;
import com.labo.utilisateur.entities.Utilisateur;
import com.labo.utilisateur.repositories.UtilisateurRepository;
import com.labo.utilisateur.services.UtilisateurServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

class UtilisateurServiceImplTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurServiceImpl utilisateurService;

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

        when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(utilisateur);

        Utilisateur result = utilisateurService.createUtilisateur(dto);
        assertEquals(dto.getEmail(), result.getEmail());
        assertEquals(dto.getNomComplet(), result.getNomComplet());

        verify(utilisateurRepository, times(1)).save(any(Utilisateur.class));
    }

    @Test
    void testGetUtilisateurById() {
        Utilisateur utilisateur = Utilisateur.builder()
                .id(1)
                .email("test@example.com")
                .nomComplet("Test User")
                .build();

        when(utilisateurRepository.findById(1)).thenReturn(Optional.of(utilisateur));

        Utilisateur result = utilisateurService.getUtilisateurById(1);
        assertEquals(utilisateur.getId(), result.getId());

        verify(utilisateurRepository, times(1)).findById(1);
    }

    @Test
    void testGetAllUtilisateurs() {
        Utilisateur utilisateur1 = Utilisateur.builder().id(1).email("test1@example.com").build();
        Utilisateur utilisateur2 = Utilisateur.builder().id(2).email("test2@example.com").build();

        when(utilisateurRepository.findAll()).thenReturn(Arrays.asList(utilisateur1, utilisateur2));

        List<Utilisateur> result = utilisateurService.getAllUtilisateurs();
        assertEquals(2, result.size());

        verify(utilisateurRepository, times(1)).findAll();
    }

    @Test
    void testUpdateUtilisateur() {
        Utilisateur utilisateur = Utilisateur.builder().id(1).email("old@example.com").build();
        UtilisateurDTO dto = UtilisateurDTO.builder().email("new@example.com").build();

        when(utilisateurRepository.findById(1)).thenReturn(Optional.of(utilisateur));
        when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(utilisateur);

        Utilisateur result = utilisateurService.updateUtilisateur(1, dto);
        assertEquals(dto.getEmail(), result.getEmail());

        verify(utilisateurRepository, times(1)).save(any(Utilisateur.class));
    }

    @Test
    void testDeleteUtilisateur() {
        doNothing().when(utilisateurRepository).deleteById(1);

        utilisateurService.deleteUtilisateur(1);

        verify(utilisateurRepository, times(1)).deleteById(1);
    }
}
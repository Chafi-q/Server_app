package entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
public class Laboratoire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String logo;

    @Column(nullable = false, unique = true)
    private String nrc;

    private boolean active;

    private LocalDate dateActivation;

	public Laboratoire() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Laboratoire(String nom, String logo, String nrc, boolean active, LocalDate dateActivation) {
		super();
		this.nom = nom;
		this.logo = logo;
		this.nrc = nrc;
		this.active = active;
		this.dateActivation = dateActivation;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDate getDateActivation() {
		return dateActivation;
	}

	public void setDateActivation(LocalDate dateActivation) {
		this.dateActivation = dateActivation;
	}
	
    
}

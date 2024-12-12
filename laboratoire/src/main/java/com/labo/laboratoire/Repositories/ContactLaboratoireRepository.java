package com.labo.laboratoire.Repositories;

import com.labo.laboratoire.entities.ContactLaboratoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactLaboratoireRepository extends JpaRepository<ContactLaboratoire, Long> {
}
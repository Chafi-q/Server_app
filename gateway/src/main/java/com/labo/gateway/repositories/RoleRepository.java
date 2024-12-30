package com.labo.gateway.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
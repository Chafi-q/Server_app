package com.labo.gateway.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail2);
}
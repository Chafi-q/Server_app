package com.labo.gateway.mappers;

import dto.UserDTO;
import entities.Role;
import entities.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setProfession(user.getProfession());
        userDTO.setNumTel(user.getNumTel());
        userDTO.setSignature(user.getSignature());
        userDTO.setIdLabo(user.getIdLabo());
        userDTO.setRoles(user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet()));
        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setProfession(userDTO.getProfession());
        user.setNumTel(userDTO.getNumTel());
        user.setSignature(userDTO.getSignature());
        user.setIdLabo(userDTO.getIdLabo());
        return user;
    }
}

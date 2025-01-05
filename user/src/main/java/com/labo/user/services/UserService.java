package com.labo.user.services;

import com.labo.user.dtos.UserDTO;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(String id, UserDTO userDTO);
    void deleteUser(String userId);
    UserDTO getUserById(String userId);
    List<UserDTO> getAllUsers();
    void sendVerificationEmail(String email);
    void forgotPassword(String username);
    List<RoleRepresentation> getUserRoles(String userId);
    List<GroupRepresentation> getUserGroups(String userId);

    AccessTokenResponse login(String username, String password);
    AccessTokenResponse refreshToken(String refreshToken);
}

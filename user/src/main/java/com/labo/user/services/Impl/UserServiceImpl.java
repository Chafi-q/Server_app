package com.labo.user.services.Impl;

import com.labo.user.dtos.UserDTO;
import com.labo.user.services.UserService;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.admin.client.token.TokenManager;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final Keycloak keycloak;

    @Value("${app.keycloak.realm}")
    private String realm;

    @Value("${app.keycloak.client-id}")
    private String clientId;

    @Value("${app.keycloak.client-secret}")
    private String clientSecret;

    @Value("${app.keycloak.serverUrl}")
    private String keycloakServerUrl;


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        log.info("Creating user with details: {}", userDTO);

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setFirstName(userDTO.getFirstName());
        userRepresentation.setLastName(userDTO.getLastName());
        userRepresentation.setUsername(userDTO.getUsername());
        userRepresentation.setEmail(userDTO.getEmail());
        userRepresentation.setEmailVerified(false);

        // Set credentials
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(userDTO.getPassword());
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        userRepresentation.setCredentials(List.of(credentialRepresentation));

        // Create user
        UsersResource usersResource = getUsersResource();
        Response response = usersResource.create(userRepresentation);

        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed to create user in Keycloak: Status code " + response.getStatus());
        }

        // Get the user ID
        String userId = CreatedResponseUtil.getCreatedId(response);
        UserResource userResource = usersResource.get(userId);

        // Now update the user with attributes
        UserRepresentation updatedUser = userResource.toRepresentation();
        Map<String, List<String>> attributes = new HashMap<>();

        if (userDTO.getProfession() != null) {
            attributes.put("profession", Collections.singletonList(userDTO.getProfession()));
        }
        if (userDTO.getNumTel() != null) {
            attributes.put("numTel", Collections.singletonList(userDTO.getNumTel()));
        }
        if (userDTO.getSignature() != null) {
            attributes.put("signature", Collections.singletonList(userDTO.getSignature()));
        }

        updatedUser.setAttributes(attributes);

        // Update the user with attributes
        userResource.update(updatedUser);
        log.info("Updated user with attributes");

        // Assign role if specified
        if (userDTO.getRole() != null) {
            assignUserRole(userId, userDTO.getRole().toUpperCase());
        }

        // Send verification email
        sendVerificationEmail(userDTO.getEmail());

        // Return the created user
        UserRepresentation createdUser = usersResource.get(userId).toRepresentation();
        return mapToDTO(createdUser);
    }


    @Override
    public UserDTO updateUser(String id, UserDTO userDTO) {
        log.info("Updating user with ID: {} and details: {}", id, userDTO);

        UsersResource usersResource = getUsersResource();
        UserResource userResource = usersResource.get(id);
        UserRepresentation userRepresentation = userResource.toRepresentation();

        // Update basic information
        userRepresentation.setFirstName(userDTO.getFirstName());
        userRepresentation.setLastName(userDTO.getLastName());
        userRepresentation.setEmail(userDTO.getEmail());
        userRepresentation.setUsername(userDTO.getUsername());

        // Update password if provided
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setValue(userDTO.getPassword());
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            userResource.resetPassword(credentialRepresentation);
            log.info("Password updated for user ID: {}", id);
        }

        // Update attributes
        Map<String, List<String>> attributes = userRepresentation.getAttributes();
        if (attributes == null) {
            attributes = new HashMap<>();
        }

        if (userDTO.getProfession() != null) {
            attributes.put("profession", Collections.singletonList(userDTO.getProfession()));
        }
        if (userDTO.getNumTel() != null) {
            attributes.put("numTel", Collections.singletonList(userDTO.getNumTel()));
        }
        if (userDTO.getSignature() != null) {
            attributes.put("signature", Collections.singletonList(userDTO.getSignature()));
        }
        userRepresentation.setAttributes(attributes);

        // Update user in Keycloak
        try {
            userResource.update(userRepresentation);
            log.info("User attributes updated for ID: {}", id);
        } catch (Exception e) {
            log.error("Failed to update user in Keycloak", e);
            throw new RuntimeException("Failed to update user in Keycloak: " + e.getMessage());
        }

        // Update role if specified
        if (userDTO.getRole() != null) {
            // Remove existing roles first to avoid duplicates
            userResource.roles().realmLevel().listAll().forEach(role ->
                    userResource.roles().realmLevel().remove(Collections.singletonList(role)));

            assignUserRole(id, userDTO.getRole().toUpperCase());
            log.info("User role updated to {} for ID: {}", userDTO.getRole(), id);
        }

        // Send verification email if email changed
        if (!userRepresentation.getEmail().equals(userDTO.getEmail())) {
            sendVerificationEmail(userDTO.getEmail());
            log.info("Verification email sent to new email address: {}", userDTO.getEmail());
        }

        // Return the updated user
        return mapToDTO(userResource.toRepresentation());
    }

    @Override
    public void deleteUser(String userId) {
        getUsersResource().delete(userId);
        log.info("User with ID {} deleted successfully", userId);
    }

    @Override
    public UserDTO getUserById(String userId) {
        UserResource userResource = getUsersResource().get(userId);
        return mapToDTO(userResource.toRepresentation());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserRepresentation> keycloakUsers = getUsersResource().list();
        return keycloakUsers.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

@Override
public void sendVerificationEmail(String email) {
    try {
        List<UserRepresentation> userRepresentations = getUsersResource().search(null, null, null, email, 0, 1);

        if (userRepresentations.isEmpty()) {
            throw new RuntimeException("No user found with email: " + email);
        }

        UserRepresentation userRepresentation = userRepresentations.get(0);

        // Send the verification email
        UserResource userResource = getUsersResource().get(userRepresentation.getId());
        userResource.sendVerifyEmail();
        log.info("Verification email sent for user with email: {}", email);
    } catch (Exception e) {
        log.error("Error sending verification email for user with email: {}", email, e);
        throw new RuntimeException("Failed to send verification email for email: " + email);
    }
}



    @Override
    public void forgotPassword(String username) {
        UsersResource usersResource = getUsersResource();
        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(username, true);

        if (userRepresentations.isEmpty()) {
            throw new RuntimeException("User not found with username: " + username);
        }

        UserRepresentation userRepresentation = userRepresentations.get(0);
        UserResource userResource = usersResource.get(userRepresentation.getId());
        userResource.executeActionsEmail(List.of("UPDATE_PASSWORD"));

        log.info("Password reset email sent for username {}", username);
    }

    @Override
    public List<RoleRepresentation> getUserRoles(String userId) {
        return getUsersResource().get(userId.toString()).roles().realmLevel().listAll();
    }

    @Override
    public List<GroupRepresentation> getUserGroups(String userId) {
        return getUsersResource().get(userId.toString()).groups();
    }

    private UsersResource getUsersResource() {
        return keycloak.realm(realm).users();
    }

private UserDTO mapToDTO(UserRepresentation userRepresentation) {
    if (userRepresentation == null) {
        throw new IllegalArgumentException("UserRepresentation cannot be null");
    }

    Map<String, List<String>> attributes = userRepresentation.getAttributes();
    log.info("Mapping attributes: {}", attributes);

    return UserDTO.builder()
            .id(userRepresentation.getId())
            .email(userRepresentation.getEmail())
            .firstName(userRepresentation.getFirstName())
            .lastName(userRepresentation.getLastName())
            .username(userRepresentation.getUsername())
            .role(getUserRoles(userRepresentation.getId()).stream()
                    .map(RoleRepresentation::getName)
                    .collect(Collectors.joining(",")))
            .profession(getAttributeValue(attributes, "profession"))
            .numTel(getAttributeValue(attributes, "numTel"))
            .signature(getAttributeValue(attributes, "signature"))
            .build();
}

    private String getAttributeValue(Map<String, List<String>> attributes, String key) {
        if (attributes == null) {
            return null;
        }
        List<String> values = attributes.get(key);
        return values != null && !values.isEmpty() ? values.get(0) : null;
    }

    @Override
    public AccessTokenResponse login(String username, String password) {
        String tokenUrl = String.format("%s/realms/%s/protocol/openid-connect/token", keycloakServerUrl, realm);

        Form form = new Form()
                .param("grant_type", "password")
                .param("username", username)
                .param("password", password)
                .param("client_id", clientId)
                .param("client_secret", clientSecret);

        return ClientBuilder.newClient()
                .target(tokenUrl)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), AccessTokenResponse.class);
    }

    @Override
    public AccessTokenResponse refreshToken(String refreshToken) {
        String tokenUrl = String.format("%s/realms/%s/protocol/openid-connect/token", keycloakServerUrl, realm);

        Form form = new Form()
                .param("grant_type", "refresh_token")
                .param("refresh_token", refreshToken)
                .param("client_id", clientId)
                .param("client_secret", clientSecret);

        return ClientBuilder.newClient()
                .target(tokenUrl)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), AccessTokenResponse.class);
    }

    private void assignUserRole(String userId, String roleName) {
        try {
            UserResource userResource = getUsersResource().get(userId);
            RoleResource roleResource = keycloak.realm(realm).roles().get(roleName);

            if (roleResource == null) {
                log.error("Role {} not found in realm", roleName);
                return;
            }

            RoleRepresentation role = roleResource.toRepresentation();
            userResource.roles().realmLevel().add(Collections.singletonList(role));

            log.info("Successfully assigned role {} to user {}", roleName, userId);
        } catch (Exception e) {
            log.error("Error assigning role {} to user {}: {}", roleName, userId, e.getMessage());
        }
    }
}

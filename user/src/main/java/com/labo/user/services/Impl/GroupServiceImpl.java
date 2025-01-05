package com.labo.user.services.Impl;

import com.labo.user.services.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class GroupServiceImpl implements GroupService {

    private final Keycloak keycloak;

    @Value("${app.keycloak.realm}")
    private String realm;

    @Override
    public void assignGroup(String userId, String groupId) {
        UserResource user = keycloak.realm(realm).users().get(userId);
        user.joinGroup(groupId);
    }

    @Override
    public void deleteGroupFromUser(String userId, String groupId) {

        UserResource user = keycloak.realm(realm).users().get(userId);
        user.leaveGroup(groupId);
    }
}

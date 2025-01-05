package com.labo.user.services;

public interface RoleService {

    void assignRole(String userId ,String roleName);
    void deleteRoleFromUser(String userId ,String roleName);

}

package com.labo.user.services;

public interface GroupService {

    void assignGroup(String userId ,String groupId);
    void deleteGroupFromUser(String userId ,String groupId);

}

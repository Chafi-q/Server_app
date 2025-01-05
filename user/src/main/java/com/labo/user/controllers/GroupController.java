package com.labo.user.controllers;

import com.labo.user.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    @PutMapping("/{groupId}/assign/users/{userId}")
    public ResponseEntity<?> assignGroup(@PathVariable String userId, @PathVariable String groupId) {

        groupService.assignGroup(userId, groupId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{groupId}/remove/users/{userId}")
    public ResponseEntity<?> unAssignGroup(@PathVariable String userId, @PathVariable String groupId) {

        groupService.deleteGroupFromUser(userId, groupId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
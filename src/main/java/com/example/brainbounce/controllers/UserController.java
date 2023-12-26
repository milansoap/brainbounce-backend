package com.example.brainbounce.controllers;

import com.example.brainbounce.models.Community;
import com.example.brainbounce.models.User;
import com.example.brainbounce.services.CommunityMemberService;
import com.example.brainbounce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final CommunityMemberService communityMemberService;

    public UserController(UserService userService,CommunityMemberService communityMemberService) {
        this.userService = userService;
        this.communityMemberService = communityMemberService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.findUserById(id)
                .map(existingUser -> {
                    user.setId(id); // Ensure the user's ID remains the same
                    User updatedUser = userService.saveUser(user);
                    return ResponseEntity.ok(updatedUser);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        if (userService.findUserById(id).isPresent()) {
            userService.deleteUserById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countUsers() {
        long userCount = userService.countUsers();
        return ResponseEntity.ok(userCount);
    }

    @GetMapping("/{userId}/communities")
    public ResponseEntity<List<Community>> getAllCommunitiesByUserId(@PathVariable Long userId) {
        List<Community> communities = communityMemberService.findAllCommunitiesByUserId(userId);
        return ResponseEntity.ok(communities);
    }

}

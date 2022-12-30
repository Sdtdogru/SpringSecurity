package com.example.springsecurity.services;

import com.example.springsecurity.models.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void saveRole() {

        UserRole userRole = new UserRole(null,"ROLE_TEST");

        UserRole added_Role = userService.saveRole(userRole);
        assertNotNull(userRole.getId());
        assertEquals(userRole.getName(), added_Role.getName());
    }
}
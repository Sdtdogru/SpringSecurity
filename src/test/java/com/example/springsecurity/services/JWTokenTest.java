package com.example.springsecurity.services;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JWTokenTest {

    @Test
    void generateToken() {
        User user = new User("Test","123456",new ArrayList<>());

        String jwt = new JWToken().generateToken(user,10);

        assertNotNull(jwt);
    }

    @Test
    void validateToken() {
        //Given
        User user = new User("Test","123456",new ArrayList<>());

        //When
        String jwt = new JWToken().generateToken(user,10);
        UsernamePasswordAuthenticationToken token = new JWToken().validateToken(jwt);

        //Then
        assertEquals(token.getPrincipal(),user.getUsername());

    }
}
package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user1 = new User();
        user1.setId(1);
        user1.setUsername("john");
        user1.setFullname("John Doe");
        user1.setPassword("encryptedpass");
        user1.setRole("USER");

        user2 = new User();
        user2.setId(2);
        user2.setUsername("jane");
        user2.setFullname("Jane Smith");
        user2.setPassword("anotherpass");
        user2.setRole("ADMIN");
    }

    @Test
    void testFindAll() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.findAll();

        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user1));

        User result = userService.findById(1);

        assertNotNull(result);
        assertEquals("john", result.getUsername());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testSave() {
        when(userRepository.save(user1)).thenReturn(user1);

        userService.save(user1);

        verify(userRepository, times(1)).save(user1);
    }

    @Test
    void testDelete() {
        userService.delete(1);
        verify(userRepository, times(1)).deleteById(1);
    }
}

package com.rodrigo.api_bradesco_funcionario;


import com.rodrigo.api_bradesco_funcionario.controllers.UserController;
import com.rodrigo.api_bradesco_funcionario.entities.Department;
import com.rodrigo.api_bradesco_funcionario.entities.User;
import com.rodrigo.api_bradesco_funcionario.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    private User user;
    private Department department;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        department = new Department(1L, "TI");

        user = new User();
        user.setId(1L);
        user.setName("João");
        user.setEmail("joao@email.com");
        user.setDepartment(department);
    }

    @Test
    public void testFindAll() {
        List<User> users = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userController.findAll();
        assertEquals(1, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userController.findById(1L);
        assertNotNull(result);
        assertEquals("João", result.getName());
    }

    @Test
    public void testInsert() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userController.insert(user);
        assertNotNull(result);
        assertEquals("João", result.getName());
    }

    @Test
    public void testUpdate() {
        User updatedUser = new User();
        updatedUser.setName("Maria");
        updatedUser.setEmail("maria@email.com");
        updatedUser.setDepartment(department);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userController.update(1L, updatedUser);
        assertEquals("Maria", result.getName());
    }

    @Test
    public void testDelete() {
        doNothing().when(userRepository).deleteById(1L);
        userController.delete(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}
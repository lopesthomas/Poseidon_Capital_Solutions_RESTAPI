package com.nnk.springboot.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.config.TestSecurityConfig;
import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(UserController.class)
@WithMockUser(roles = "ADMIN")
@Import(TestSecurityConfig.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    public void testShowUserList() throws Exception {
        when(userService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    public void testAddUserForm() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }

    @Test
    public void testValidateUser_valid() throws Exception {
        mockMvc.perform(post("/user/validate")
                        .with(csrf())
                        .param("username", "Test Username")
                        .param("fullname", "Test Fullname")
                        .param("role", "USER")
                        .param("password", "12345678"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));

        verify(userService).save(any(User.class));
    }

    @Test
    public void testValidateUser_invalid() throws Exception {
        mockMvc.perform(post("/user/validate")
                        .with(csrf())
                        .param("username", "")
                        .param("password", ""))
                .andExpect(model().attributeHasErrors("user"))
                .andExpect(view().name("user/add"));
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("Test Username");
        user.setPassword("encoded");
        when(userService.findById(1)).thenReturn(user);

        mockMvc.perform(get("/user/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testUpdateUser_valid() throws Exception {
        mockMvc.perform(post("/user/update/1")
                        .with(csrf())
                        .param("username", "Test Username")
                        .param("fullname", "Test Fullname")
                        .param("role", "USER")
                        .param("password", "newpass"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));

        verify(userService).save(any(User.class));
    }

    @Test
    public void testUpdateUser_invalid() throws Exception {
        mockMvc.perform(post("/user/update/1")
                        .with(csrf())
                        .param("username", "")
                        .param("password", ""))
                .andExpect(model().attributeHasErrors("user"))
                .andExpect(view().name("user/update"));
    }

    @Test
    public void testDeleteUser_found() throws Exception {
        User user = new User();
        user.setId(1);
        when(userService.findById(1)).thenReturn(user);

        mockMvc.perform(get("/user/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));

        verify(userService).delete(1);
    }

    @Test
    public void testDeleteUser_notFound() throws Exception {
        when(userService.findById(999)).thenReturn(null);

        mockMvc.perform(get("/user/delete/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }
}

package com.example.springsecurity.controllers;
import com.example.springsecurity.models.User;
import com.example.springsecurity.repos.TodoListRepository;
import com.example.springsecurity.repos.UserRepository;
import com.example.springsecurity.services.UserRoleService;
import com.example.springsecurity.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest({TodoController.class,UserController.class})
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoListService todoListService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private TodoListRepository todoListRepository;
    @MockBean
    private UserService userService;

    @MockBean
    private TaskService taskService;

    @MockBean
    private UserRoleService userRoleService;
    @MockBean

    private WebApplicationContext context;

    User test_user = new User(null, "test user","username","123456",new ArrayList<>());
    TodoList todo = new TodoList(null, "test todo", null, new ArrayList<>(),System.currentTimeMillis(),System.currentTimeMillis());
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void contextLoads() throws Exception {
        assertThat(todo).isNotNull();
        assertThat(test_user).isNotNull();
    }

//    @Test
//    void getTodos() {
//
//    }
//

    @Test
    void getTodos() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/todos");
        mvc.perform(requestBuilder).andExpect(status().isUnauthorized());

    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void getTodoByUserID() throws Exception {
        //given
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/todos");
        //when
        MvcResult result = mvc.perform(requestBuilder).andReturn();

        //then
        assertEquals( result.getResponse().getStatus(),  200);
    }


    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void addTodo() throws Exception {
        //given
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content( objectMapper.writeValueAsString(test_user) )
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON);
        var y =mvc.perform(requestBuilder).andReturn().getResponse().getStatus();
        var x =mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString();
        System.out.println(x);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/{id}/todos",1)
//                .with(csrf())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content( objectMapper.writeValueAsString(todo) )
//                .accept(MediaType.APPLICATION_JSON);

        //when & then
         //mvc.perform(requestBuilder).andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }




}
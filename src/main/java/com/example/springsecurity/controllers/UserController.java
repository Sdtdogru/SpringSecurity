package com.example.springsecurity.controllers;

import com.example.springsecurity.models.User;
import com.example.springsecurity.services.UserRoleService;
import com.example.springsecurity.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRoleService userRoleService;
//    public final TodoListService todoListService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers()
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
        User new_user = userService.saveUser(user);
        System.out.println(new_user);
        return ResponseEntity.created(uri).body(new_user);
    }

    @PostMapping("/users/roles")
    public ResponseEntity<?> addRoleToUser(@RequestBody UserRoleForm form)
    {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok("Successful!");
    }

//    @Secured("ROLE_ADMIN")
//    @PostMapping("/users/{id}/todos")
//    @ResponseBody
//    public ResponseEntity<TodoList> addTodo(@PathVariable Long id, @RequestBody TodoList todoList)
//    {
//        todoList.setUser(userService.getUserById(id));
//        return new ResponseEntity(todoListService.addTodoList(todoList), HttpStatus.CREATED);
//    }
}
@Data
class UserRoleForm
{
    private String username;
    private String roleName;
}

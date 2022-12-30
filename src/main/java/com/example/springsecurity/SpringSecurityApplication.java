package com.example.springsecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@Slf4j

public class SpringSecurityApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringSecurityApplication.class, args);

        System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));

    }

    @Bean
    BCryptPasswordEncoder passwordEncoder()
    {
       return  new BCryptPasswordEncoder();
    }

//    @Bean
//    CommandLineRunner run(UserService userService, TodoListService todoListService,
//                          TaskService taskService)
//    {
//        return args->
//        {
//            userService.saveRole(new UserRole(null,"ROLE_USER"));
//            userService.saveRole(new UserRole(null,"ROLE_MANAGER"));
//            userService.saveRole(new UserRole(null,"ROLE_ADMIN"));
//
//            userService.saveUser(new User(null,"rafet","rdurgut","12345",new ArrayList<>()));
//
//            userService.addRoleToUser("rdurgut","ROLE_USER");
//            userService.addRoleToUser("rdurgut","ROLE_ADMIN");
//
//
//            TodoList td = new TodoList();
//            td.setUser(userService.getUser("rdurgut"));
//            td.setTitle("Test title");
//            todoListService.addTodoList(td);
//
//
//            Task task = new Task("Test Task","Test description",null);
//            task.setTodoList(todoListService.getTodoList(td.getId()));
//            task.setUser(userService.getUser("rdurgut"));
//            taskService.addTask(task);
//
//            Comment comment = new Comment();
//            comment.setComment("Test comment");
//            comment.setUser(userService.getUser("rdurgut"));
//            taskService.addComment(comment, task.getId());
//
//
//        };
//    }
}

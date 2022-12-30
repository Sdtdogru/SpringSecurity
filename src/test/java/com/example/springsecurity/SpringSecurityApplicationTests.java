package com.example.springsecurity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ComponentScan({"com.example.springsecurity","com.example.springsecurity.repos"})

class SpringSecurityApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void TestEt()
    {
        //Given
        int a=10, b=5;
        //When
        int c = a+b;
        //Test
        Assertions.assertEquals(c, 15);
    }
}

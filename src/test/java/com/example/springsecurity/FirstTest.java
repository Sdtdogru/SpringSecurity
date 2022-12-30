package com.example.springsecurity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FirstTest {

    @Test
    public void testFunction()
    {
        int a=5;
        int b=10;

        int c = a+b;
        assertEquals(c,15);
    }
}

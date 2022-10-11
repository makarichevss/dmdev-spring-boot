package org.example.service;

import org.example.IntegrationTestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

class GreetingServiceTest extends IntegrationTestBase {

    @Autowired
    private GreetingService greetingService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGreeting() {
        String expectedMessage = "Hello world!2";
        assertEquals(expectedMessage, greetingService.greet());

    }
}
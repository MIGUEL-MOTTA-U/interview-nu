package com.luke_industries.rest_app.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ExampleApi.class)
class ExampleApiTest {

    private final ExampleApi exampleApi = new ExampleApi();
    // @MockitoBean
    //private ExampleService exampleService;

    @Test
    void health() {
        ResponseEntity<String> response = exampleApi.health();
        assertEquals("OK", response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }
}
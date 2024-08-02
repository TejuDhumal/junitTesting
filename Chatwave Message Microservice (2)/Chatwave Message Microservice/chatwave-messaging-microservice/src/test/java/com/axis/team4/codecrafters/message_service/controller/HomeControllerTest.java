package com.axis.team4.codecrafters.message_service.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//@Disabled
public class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    public void setUp() {
        homeController = new HomeController();
    }

    @Test
    public void testHomePageHandler() {
        ResponseEntity<String> response = homeController.homePageHandler();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Chat Wave Homepage", response.getBody());
    }
}

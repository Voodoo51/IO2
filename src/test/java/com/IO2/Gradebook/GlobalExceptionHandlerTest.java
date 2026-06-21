package com.IO2.Gradebook.exceptionHandlers;

import com.IO2.Gradebook.exceptions.InvalidLoginException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleInvalidLogin() {
        String errorMessage = "Błędne dane logowania";
        InvalidLoginException exception = new InvalidLoginException(errorMessage);

        ResponseEntity<?> response = exceptionHandler.handleInvalidLogin(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertNotNull(body);
        assertEquals(errorMessage, body.get("message"));
    }
}
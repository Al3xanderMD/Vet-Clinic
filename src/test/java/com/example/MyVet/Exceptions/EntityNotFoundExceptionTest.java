package com.example.MyVet.Exceptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityNotFoundExceptionTest {

    @Test
    public void testConstructorWithId() {
        // Given
        String entityName = "User";
        String id = "123";

        // When
        EntityNotFoundException exception = new EntityNotFoundException(entityName, id);

        // Then
        String expectedMessage = "User with id 123 not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testConstructorWithoutId() {
        // Given
        String entityName = "User";

        // When
        EntityNotFoundException exception = new EntityNotFoundException(entityName);

        // Then
        String expectedMessage = "User not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
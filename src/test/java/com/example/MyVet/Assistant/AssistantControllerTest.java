package com.example.MyVet.Assistant;

import com.example.MyVet.Cabinet.Cabinet;

import com.example.MyVet.MedicalStaff.MedicalStaff;
import com.example.MyVet.User.users.User;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AssistantControllerTest {

    @Mock
    private AssistantService assistantService;

    @InjectMocks
    private AssistantController assistantController;

    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        javax.validation.ValidatorFactory factory = javax.validation.Validation.byDefaultProvider().configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
    }

    @Test
    void getAll_ReturnsListOfAssistants() {
        // Arrange
        List<Assistant> expectedAssistants = new ArrayList<>();
        expectedAssistants.add(new Assistant("1", new MedicalStaff()));
        expectedAssistants.add(new Assistant("2", new MedicalStaff("id", "azi",1000, 10, new User(), new Cabinet())));

        when(assistantService.getAll()).thenReturn(expectedAssistants);

        // Act
        List<Assistant> result = assistantController.getAll();

        // Assert
        assertEquals(expectedAssistants, result);
        verify(assistantService, times(1)).getAll();
    }

    @Test
    void getById_ExistingId_ReturnsAssistant() {
        // Arrange
        Assistant assistant = new Assistant("1", new MedicalStaff("id", "azi",1000, 10, new User(), new Cabinet()));

        when(assistantService.getById("1")).thenReturn(Optional.of(assistant));

        // Act
        ResponseEntity<Assistant> response = assistantController.getById("1");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assistant, response.getBody());
        verify(assistantService, times(1)).getById("1");
    }

    @Test
    void getById_NonExistingId_ReturnsNotFoundStatus() {
        // Arrange
        when(assistantService.getById("1")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Assistant> response = assistantController.getById("1");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(assistantService, times(1)).getById("1");
    }

}

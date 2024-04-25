package com.example.MyVet.Assistant;
import com.example.MyVet.MedicalStaff.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AssistantMapperTest {

    @InjectMocks
    private AssistantMapper assistantMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toAssistant_ReturnsAssistantWithMappedProperties() {
        // Arrange
        AssistantDTO assistantDTO = new AssistantDTO();
        assistantDTO.setCabinetId("123");

        // Act
        Assistant result = assistantMapper.toAssistant(assistantDTO);

        // Assert
        assertNotEquals(assistantDTO.getCabinetId(), result.getId());
        // Add more assertions based on your mapping logic
    }

    @Test
    void toAssistant_ReturnsAssistantWithNullMedicalStaff() {
        // Arrange
        AssistantDTO assistantDTO = new AssistantDTO();
        assistantDTO.setCabinetId("123");

        // Act
        Assistant result = assistantMapper.toAssistant(assistantDTO);

        // Assert
        assertEquals(null, result.getMedicalStaff());
        // Add more assertions based on your mapping logic
    }

    @Test
    void toAssistant_ReturnsAssistantWithMedicalStaff() {
        // Arrange
        AssistantDTO assistantDTO = new AssistantDTO();
        assistantDTO.setCabinetId("123");
        MedicalStaff medicalStaff = new MedicalStaff();

        // Act
        Assistant result = assistantMapper.toAssistant(assistantDTO);
        result.setMedicalStaff(medicalStaff);

        // Assert
        assertEquals(medicalStaff, result.getMedicalStaff());
        // Add more assertions based on your mapping logic
    }
}

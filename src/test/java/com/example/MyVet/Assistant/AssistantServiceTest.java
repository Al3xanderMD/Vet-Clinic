package com.example.MyVet.Assistant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.MyVet.MedicalStaff.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AssistantServiceTest {

    @Mock
    private AssistantRepository assistantRepository;

    @InjectMocks
    private AssistantService assistantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void add_ReturnsAssistant() {
        // Arrange
        Assistant assistant = new Assistant();
        when(assistantRepository.save(assistant)).thenReturn(assistant);

        // Act
        Assistant result = assistantService.add(assistant);

        // Assert
        assertEquals(assistant, result);
        verify(assistantRepository, times(1)).save(assistant);
    }

    @Test
    void getAll_ReturnsListOfAssistants() {
        // Arrange
        List<Assistant> expectedAssistants = new ArrayList<>();
        expectedAssistants.add(new Assistant("1", new MedicalStaff()));
        expectedAssistants.add(new Assistant("2", new MedicalStaff()));

        when(assistantRepository.findAll()).thenReturn(expectedAssistants);

        // Act
        List<Assistant> result = assistantService.getAll();

        // Assert
        assertEquals(expectedAssistants, result);
        verify(assistantRepository, times(1)).findAll();
    }

    @Test
    void getById_ExistingId_ReturnsOptionalAssistant() {
        // Arrange
        Assistant assistant = new Assistant("1", new MedicalStaff());

        when(assistantRepository.findById("1")).thenReturn(Optional.of(assistant));

        // Act
        Optional<Assistant> result = assistantService.getById("1");

        // Assert
        assertEquals(Optional.of(assistant), result);
        verify(assistantRepository, times(1)).findById("1");
    }

    @Test
    void getById_NonExistingId_ReturnsEmptyOptional() {
        // Arrange
        when(assistantRepository.findById("1")).thenReturn(Optional.empty());

        // Act
        Optional<Assistant> result = assistantService.getById("1");

        // Assert
        assertEquals(Optional.empty(), result);
        verify(assistantRepository, times(1)).findById("1");
    }

    @Test
    void update_ExistingId_ReturnsUpdatedAssistant() {
        // Arrange
        String id = "1";
        AssistantDTO assistantDTO = new AssistantDTO();
        Assistant existingAssistant = new Assistant(id, new MedicalStaff());

        when(assistantRepository.findById(id)).thenReturn(Optional.of(existingAssistant));
        when(assistantRepository.save(existingAssistant)).thenReturn(existingAssistant);

        // Act
        Assistant result = assistantService.update(id, assistantDTO);

        // Assert
        assertEquals(existingAssistant, result);
        verify(assistantRepository, times(1)).findById(id);
        verify(assistantRepository, times(1)).save(existingAssistant);
    }

    @Test
    void update_NonExistingId_ThrowsException() {
        // Arrange
        String id = "1";
        AssistantDTO assistantDTO = new AssistantDTO();

        when(assistantRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        try {
            assistantService.update(id, assistantDTO);
        } catch (RuntimeException e) {
            assertEquals("Assistant not found", e.getMessage());
        }

        verify(assistantRepository, times(1)).findById(id);
        verify(assistantRepository, never()).save(any());
    }

}

package com.example.MyVet.Cabinet;

import com.example.MyVet.DrugStock.DrugStock;
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

class CabinetControllerTest {

    @Mock
    private CabinetService cabinetService;

    @InjectMocks
    private CabinetController cabinetController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_ReturnsListOfCabinets() {
        // Arrange
        List<Cabinet> expectedCabinets = new ArrayList<>();
        expectedCabinets.add(new Cabinet("id1", "cabinet1", new DrugStock()));
        expectedCabinets.add(new Cabinet("id2", "cabinet2", new DrugStock()));

        when(cabinetService.getAll()).thenReturn(expectedCabinets);

        // Act
        List<Cabinet> result = cabinetController.getAll();

        // Assert
        assertEquals(expectedCabinets, result);
        verify(cabinetService, times(1)).getAll();
    }

    @Test
    void add_ValidCabinetDTO_ReturnsCreatedResponseWithNewCabinet() {
        // Arrange
        CabinetDTO cabinetDTO = new CabinetDTO();
        Cabinet newCabinet = new Cabinet("id", "cabinet", new DrugStock());

        when(cabinetService.create(cabinetDTO)).thenReturn(newCabinet);

        // Act
        ResponseEntity<Cabinet> response = cabinetController.add(cabinetDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newCabinet, response.getBody());
        verify(cabinetService, times(1)).create(cabinetDTO);
    }

    @Test
    void getById_ExistingId_ReturnsOkResponseWithCabinet() {
        // Arrange
        String id = "1";
        Cabinet cabinet = new Cabinet(id, "cabinet1", new DrugStock());

        when(cabinetService.getById(id)).thenReturn(Optional.of(cabinet));

        // Act
        ResponseEntity<Cabinet> response = cabinetController.getById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cabinet, response.getBody());
        verify(cabinetService, times(1)).getById(id);
    }

    @Test
    void getById_NonExistingId_ReturnsNotFoundResponse() {
        // Arrange
        String id = "1";

        when(cabinetService.getById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Cabinet> response = cabinetController.getById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(cabinetService, times(1)).getById(id);
    }

    @Test
    void update_ExistingIdAndValidCabinetDTO_ReturnsOkResponseWithUpdatedCabinet() {
        // Arrange
        String id = "1";
        CabinetDTO cabinetDTO = new CabinetDTO();
        Cabinet updatedCabinet = new Cabinet(id, "cabinet", new DrugStock());

        when(cabinetService.update(id, cabinetDTO)).thenReturn(updatedCabinet);

        // Act
        ResponseEntity<Cabinet> response = cabinetController.update(id, cabinetDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCabinet, response.getBody());
        verify(cabinetService, times(1)).update(id, cabinetDTO);
    }

    @Test
    void deleteById_DeletesCabinetAndReturnsNoContentResponse() {
        // Arrange
        String id = "1";

        // Act
        ResponseEntity<Cabinet> response = cabinetController.deleteById(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cabinetService, times(1)).deleteById(id);
    }
}

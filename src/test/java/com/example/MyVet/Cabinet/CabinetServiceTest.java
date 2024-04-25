package com.example.MyVet.Cabinet;

import com.example.MyVet.DrugStock.DrugStockService;
import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CabinetServiceTest {

    @Mock
    private CabinetRepository cabinetRepository;

    @InjectMocks
    private CabinetService cabinetService;

    @Mock
    private DrugStockService drugStockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void create_ValidCabinetDTO_ReturnsCreatedCabinet() {
//        // Arrange
//        CabinetDTO cabinetDTO = new CabinetDTO();
//        Cabinet cabinet = new Cabinet();
//        String drugStockId = "123";
//        cabinetDTO.setDrugStockId(drugStockId);
//
//        when(drugStockService.getById(drugStockId)).thenReturn(Optional.empty());
//        when(cabinetRepository.save(any(Cabinet.class))).thenReturn(cabinet);
//
//        // Act
//        Cabinet result = cabinetService.create(cabinetDTO);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(cabinet, result);
//        verify(drugStockService, times(1)).getById(drugStockId);
//        verify(cabinetRepository, times(1)).save(any(Cabinet.class));
//    }

    @Test
    void getAll_ReturnsListOfCabinets() {
        // Arrange
        List<Cabinet> cabinets = Arrays.asList(new Cabinet(), new Cabinet());
        when(cabinetRepository.findAll()).thenReturn(cabinets);

        // Act
        List<Cabinet> result = cabinetService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(cabinets, result);
        verify(cabinetRepository, times(1)).findAll();
    }

    @Test
    void getById_ExistingId_ReturnsOptionalCabinet() {
        // Arrange
        String id = "123";
        Cabinet cabinet = new Cabinet();
        when(cabinetRepository.findById(id)).thenReturn(Optional.of(cabinet));

        // Act
        Optional<Cabinet> result = cabinetService.getById(id);

        // Assert
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(cabinet, result.get());
        verify(cabinetRepository, times(1)).findById(id);
    }

    @Test
    void getById_NonexistentId_ReturnsEmptyOptional() {
        // Arrange
        String id = "123";
        when(cabinetRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<Cabinet> result = cabinetService.getById(id);

        // Assert
        assertNotNull(result);
        assertFalse(result.isPresent());
        verify(cabinetRepository, times(1)).findById(id);
    }

//    @Test
//    void update_ExistingIdAndValidCabinetDTO_ReturnsUpdatedCabinet() {
//        // Arrange
//        String id = "123";
//        CabinetDTO cabinetDTO = new CabinetDTO();
//        Cabinet existingCabinet = new Cabinet();
//        Cabinet updatedCabinet = new Cabinet();
//        cabinetDTO.setDrugStockId("456");
//
//        when(cabinetRepository.findById(id)).thenReturn(Optional.of(existingCabinet));
//        when(cabinetRepository.save(updatedCabinet)).thenReturn(updatedCabinet);
//
//        // Act
//        Cabinet result = cabinetService.update(id, cabinetDTO);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(updatedCabinet, result);
//        verify(cabinetRepository, times(1)).findById(id);
//        verify(cabinetRepository, times(1)).save(updatedCabinet);
//    }

    @Test
    void update_NonexistentId_ThrowsEntityNotFoundException() {
        // Arrange
        String id = "123";
        CabinetDTO cabinetDTO = new CabinetDTO();

        when(cabinetRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> cabinetService.update(id, cabinetDTO));
        verify(cabinetRepository, times(1)).findById(id);
        verify(cabinetRepository, never()).save(any(Cabinet.class));
    }

    @Test
    void deleteById_ExistingId_DeletesCabinet() {
        // Arrange
        String id = "123";

        // Act
        cabinetService.deleteById(id);

        // Assert
        verify(cabinetRepository, times(1)).deleteById(id);
    }
//    @Test
//    public void testUpdateWhenCabinetExists() {
//        MockitoAnnotations.openMocks(this);
//
//        String id = "1";
//        CabinetDTO cabinetDTO = new CabinetDTO(/* DTO values */);
//
//        Cabinet existingCabinet = new Cabinet();
//        existingCabinet.setId(id);
//
//        Cabinet updatedCabinet = new Cabinet();
//        updatedCabinet.setId(id);
//        // set other fields based on the DTO values
//
//        when(cabinetRepository.findById(id)).thenReturn(Optional.of(existingCabinet));
//        when(cabinetRepository.save(updatedCabinet)).thenReturn(updatedCabinet);
//
//        Cabinet result = cabinetService.update(id, cabinetDTO);
//
//        assertEquals(updatedCabinet, result);
//
//        verify(cabinetRepository, times(1)).findById(id);
//        verify(cabinetRepository, times(1)).save(updatedCabinet);
//    }

    @Test
    public void testUpdateWhenCabinetDoesNotExist() {
        MockitoAnnotations.openMocks(this);

        String id = "1";
        CabinetDTO cabinetDTO = new CabinetDTO(/* DTO values */);

        when(cabinetRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> cabinetService.update(id, cabinetDTO));

        verify(cabinetRepository, times(1)).findById(id);
        verify(cabinetRepository, never()).save(any());
    }

//    @Test
//    public void testCreate() {
//        MockitoAnnotations.openMocks(this);
//
//        CabinetDTO cabinetDTO = new CabinetDTO(/* DTO values */);
//
//        Cabinet createdCabinet = new Cabinet();
//        // set fields based on the DTO values
//
//        when(cabinetRepository.save(any())).thenReturn(createdCabinet);
//
//        Cabinet result = cabinetService.create(cabinetDTO);
//
//        assertEquals(createdCabinet, result);
//
//        verify(cabinetRepository, times(1)).save(any());
//    }
}

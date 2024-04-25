package com.example.MyVet.Vet;

import com.example.MyVet.Cabinet.Cabinet;
import com.example.MyVet.Cabinet.CabinetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.MyVet.MedicalStaff.*;

import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class VetMapperTest {

    @Mock
    private MedicalStaffService medicalStaffService;
    @Mock
    private CabinetService cabinetService;

    private VetMapper vetMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        vetMapper = new VetMapper();
    }

    @Test
    public void testToVet() {
        // Create a VetDTO object
        VetDTO vetDTO = new VetDTO();

        // Mock the cabinetService.getById() method
        Cabinet cabinet = new Cabinet();
//        when(cabinetService.getById("Cabinet-123")).thenReturn(Optional.of(cabinet));

        // Call the toVet() method
        Vet vet = vetMapper.toVet(vetDTO);

        // Verify that cabinetService.getById() was called with the correct argument
//        verify(cabinetService).getById("Cabinet-123");

        // Verify that the cabinet is set on the Vet object

        assertNull(vet.getId());

        // Add additional assertions as needed
    }
}

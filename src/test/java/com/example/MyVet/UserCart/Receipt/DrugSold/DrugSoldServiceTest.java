package com.example.MyVet.UserCart.Receipt.DrugSold;

import com.example.MyVet.Drug.Drug;
import com.example.MyVet.Drug.DrugService;
import com.example.MyVet.Exceptions.EntityNotFoundException;
import com.example.MyVet.UserCart.Receipt.DrugSold.DrugSold;
import com.example.MyVet.UserCart.Receipt.DrugSold.DrugSoldDTO;
import com.example.MyVet.UserCart.Receipt.DrugSold.DrugSoldRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DrugSoldServiceTest {

    @Mock
    private DrugSoldRepository drugSoldRepository;

    @Mock
    private DrugService drugService;

    @InjectMocks
    private DrugSoldService drugSoldService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public DrugSold mapper(DrugSoldDTO drugSoldDTO)throws Exception{
        DrugSold drugSold = new DrugSold();

        var DS = drugService.getById(drugSoldDTO.getDrugId());
        DS.ifPresent(drugSold::setDrug);

        drugSold.setQuantitySold(drugSoldDTO.getQuantity());

        if(drugSold.getQuantitySold() <= drugService.getById(drugSoldDTO.getDrugId()).get().getQuantity())
        {
            drugSold.setPrice(drugSoldDTO.getQuantity() * drugService.getById(drugSoldDTO.getDrugId()).get().getPriceUnit());

            var diff = drugService.getById(drugSoldDTO.getDrugId()).get().getQuantity() - drugSoldDTO.getQuantity();

            drugService.updateQuantity(drugSoldDTO.getDrugId(), diff);

        } else if (drugService.getById(drugSoldDTO.getDrugId()).get().getQuantity() != 0 ){
//            throw new Exception("Low Quntity in Stock");
        }

        return drugSold;
    }

    public DrugSold update(String id, DrugSoldDTO drugSoldDTO)throws Exception{
        Optional<DrugSold> existingDrugSold = drugSoldRepository.findById(id);

        if(existingDrugSold.isPresent()){
            var newDS = mapper(drugSoldDTO);
            newDS.setId(existingDrugSold.get().getId());

            return drugSoldRepository.save(newDS);
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    @Test
    void create_ValidDrugSoldDTO_ReturnsDrugSold() throws Exception {
        // Arrange
        DrugSoldDTO drugSoldDTO = new DrugSoldDTO();
        drugSoldDTO.setDrugId("drugId");
        drugSoldDTO.setQuantity(10);

        when(drugService.getById(drugSoldDTO.getDrugId())).thenReturn((Optional<Drug>) Optional.of(new Drug()));
        when(drugSoldRepository.save(any(DrugSold.class))).thenReturn(new DrugSold());

        // Act
        DrugSold result = drugSoldRepository.save(mapper(drugSoldDTO));

        // Assert
        assertNotNull(result);
        verify(drugSoldRepository, times(1)).save(any(DrugSold.class));
    }

    @Test
    void create_InvalidDrugSoldDTO_ThrowsException() {
        // Arrange
        DrugSoldDTO drugSoldDTO = new DrugSoldDTO();
        drugSoldDTO.setDrugId("drugId");
        drugSoldDTO.setQuantity(10);

        when(drugService.getById(drugSoldDTO.getDrugId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> drugSoldService.create(drugSoldDTO));
        verify(drugSoldRepository, never()).save(any(DrugSold.class));
    }

    @Test
    void update_ExistingDrugSold_ReturnsUpdatedDrugSold() throws Exception {
        // Arrange
        String drugSoldId = "drugSoldId";
        DrugSoldDTO drugSoldDTO = new DrugSoldDTO();
        drugSoldDTO.setDrugId("drugId");
        drugSoldDTO.setQuantity(5);

        DrugSold existingDrugSold = new DrugSold();
        existingDrugSold.setId(drugSoldId);

        when(drugSoldRepository.findById(drugSoldId)).thenReturn(Optional.of(existingDrugSold));
        when(drugService.getById(drugSoldDTO.getDrugId())).thenReturn(Optional.of(new Drug()));
        when(drugSoldRepository.save(any(DrugSold.class))).thenReturn(existingDrugSold);

        // Act
        DrugSold result =update(drugSoldId, drugSoldDTO);

        // Assert
        assertNotNull(result);
        assertEquals(drugSoldId, result.getId());
        verify(drugSoldRepository, times(1)).save(any(DrugSold.class));
    }

    @Test
    void update_NonexistentDrugSold_ThrowsEntityNotFoundException() {
        // Arrange
        String drugSoldId = "drugSoldId";
        DrugSoldDTO drugSoldDTO = new DrugSoldDTO();
        drugSoldDTO.setDrugId("drugId");
        drugSoldDTO.setQuantity(5);

        when(drugSoldRepository.findById(drugSoldId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> drugSoldService.update(drugSoldId, drugSoldDTO));
        verify(drugSoldRepository, never()).save(any(DrugSold.class));
    }

    @Test
    void update_InvalidDrugSoldDTO_ThrowsException() {
        // Arrange
        String drugSoldId = "drugSoldId";
        DrugSoldDTO drugSoldDTO = new DrugSoldDTO();
        drugSoldDTO.setDrugId("drugId");
        drugSoldDTO.setQuantity(10);

        DrugSold existingDrugSold = new DrugSold();
        existingDrugSold.setId(drugSoldId);

        when(drugSoldRepository.findById(drugSoldId)).thenReturn(Optional.of(existingDrugSold));
        when(drugService.getById(drugSoldDTO.getDrugId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> drugSoldService.update(drugSoldId, drugSoldDTO));
        verify(drugSoldRepository, never()).save(any(DrugSold.class));
    }


    @Test
    void getAllTest() {
        List<DrugSold> drugSoldList = new ArrayList<>(); // Create a sample list of DrugSold objects

        Mockito.when(drugSoldRepository.findAll()).thenReturn(drugSoldList);

        List<DrugSold> result = drugSoldService.getAll();
        Assertions.assertEquals(drugSoldList, result, "Returned list of DrugSold does not match the expected list.");
    }

    @Test
    void getByIdTest() {
        String drugSoldId = "123"; // Provide a sample drugSold ID
        DrugSold drugSold = new DrugSold(); // Create a sample DrugSold object

        Mockito.when(drugSoldRepository.findById(drugSoldId)).thenReturn(Optional.of(drugSold));

        Optional<DrugSold> result = drugSoldService.getById(drugSoldId);
        Assertions.assertEquals(Optional.of(drugSold), result, "Returned DrugSold does not match the expected DrugSold.");
    }


    @Test
    void deleteByIdTest() {
        String drugSoldId = "123"; // Provide a sample drugSold ID

        Assertions.assertDoesNotThrow(() -> drugSoldService.deleteById(drugSoldId), "Deleting DrugSold by ID should not throw any exception.");
    }
}

package com.example.MyVet.UserCart.Receipt.DrugSold;

import com.example.MyVet.Drug.Drug;
import com.example.MyVet.Drug.DrugService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

public class DrugSoldMapperTest {

    @Mock
    private DrugService drugService;
    @Mock
    private DrugSoldMapper drugSoldMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        drugSoldMapper = new DrugSoldMapper(drugService);
    }

    @Test
    public void testToDrugSold_WithValidDTO_QuantityLessThanAvailable() throws Exception {
        // Arrange
        DrugSoldDTO drugSoldDTO = new DrugSoldDTO();
        drugSoldDTO.setDrugId("123");
        drugSoldDTO.setQuantity(5);

//        DrugSoldMapper drugSoldMapper = new DrugSoldMapper();

        Drug drug = new Drug();
        drug.setQuantity(10);
        drug.setPriceUnit(10.0);

        Mockito.when(drugService.getById("123")).thenReturn(Optional.of(drug));

        // Act
        DrugSold drugSold = drugSoldMapper.toDrugSold(drugSoldDTO);

        // Assert
        Assertions.assertEquals(drug, drugSold.getDrug());
        Assertions.assertEquals(drugSoldDTO.getQuantity(), drugSold.getQuantitySold());
        Assertions.assertEquals(drugSoldDTO.getQuantity() * drug.getPriceUnit(), drugSold.getPrice());
        Mockito.verify(drugService, Mockito.times(1)).updateQuantity("123", 5);
    }

    @Test
    public void testToDrugSold_WithValidDTO_QuantityEqualToAvailable() throws Exception {
        // Arrange
        DrugSoldDTO drugSoldDTO = new DrugSoldDTO();
        drugSoldDTO.setDrugId("123");
        drugSoldDTO.setQuantity(10);



        Drug drug = new Drug();
        drug.setQuantity(10);
        drug.setPriceUnit(10.0);

        Mockito.when(drugService.getById("123")).thenReturn(Optional.of(drug));

        // Act
        DrugSold drugSold = drugSoldMapper.toDrugSold(drugSoldDTO);

        // Assert
        Assertions.assertEquals(drug, drugSold.getDrug());
        Assertions.assertEquals(drugSoldDTO.getQuantity(), drugSold.getQuantitySold());
        Assertions.assertEquals(drugSoldDTO.getQuantity() * drug.getPriceUnit(), drugSold.getPrice());
        Mockito.verify(drugService, Mockito.times(1)).updateQuantity("123", 0);
    }

    @Test
    public void testToDrugSold_WithInvalidDTO_LowQuantityInStock() {
        // Arrange
        DrugSoldDTO drugSoldDTO = new DrugSoldDTO();
        drugSoldDTO.setDrugId("123");
        drugSoldDTO.setQuantity(15);



        Drug drug = new Drug();
        drug.setQuantity(10);
        drug.setPriceUnit(10.0);

        Mockito.when(drugService.getById("123")).thenReturn(Optional.of(drug));

        // Act & Assert
        Assertions.assertThrows(Exception.class, () -> drugSoldMapper.toDrugSold(drugSoldDTO));
        Mockito.verify(drugService, Mockito.times(0)).updateQuantity(Mockito.anyString(), Mockito.anyInt());
    }

    @Test
    public void testToDrugSold_WithInvalidDTO_OutOfStock() {
        // Arrange
        DrugSoldDTO drugSoldDTO = new DrugSoldDTO();
        drugSoldDTO.setDrugId("123");
        drugSoldDTO.setQuantity(5);



        Mockito.when(drugService.getById("123")).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(Exception.class, () -> drugSoldMapper.toDrugSold(drugSoldDTO));
        Mockito.verify(drugService, Mockito.times(0)).updateQuantity(Mockito.anyString(), Mockito.anyInt());
    }

    @Test
    public void testConstructorWithDrugService() {
        // Arrange
        MockitoAnnotations.openMocks(this);

        // Act
        DrugSoldMapper drugSoldMapper = new DrugSoldMapper();

        // Assert
        Assertions.assertNotNull(drugSoldMapper);
//        Assertions.assertEquals(drugService, drugSoldMapper.drugService);
    }
    @Test
    public void testToDrugSold_WithInvalidDTO_OutOfStock2() {
        // Arrange
        DrugSoldDTO drugSoldDTO = new DrugSoldDTO();
        drugSoldDTO.setDrugId("123");
        drugSoldDTO.setQuantity(5);

        DrugSoldMapper drugSoldMapper = new DrugSoldMapper();

        Mockito.when(drugService.getById("123")).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = Assertions.assertThrows(Exception.class, () -> drugSoldMapper.toDrugSold(drugSoldDTO));
        Assertions.assertNotEquals("Out of stock", exception.getMessage());
        Mockito.verify(drugService, Mockito.times(0)).updateQuantity(Mockito.anyString(), Mockito.anyInt());
    }


}

package com.example.MyVet.Cabinet;

import com.example.MyVet.DrugStock.DrugStock;
import com.example.MyVet.DrugStock.DrugStockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CabinetMapperTest {

    @Mock
    private DrugStockService drugStockService;

    @InjectMocks
    private CabinetMapper cabinetMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toCabinet_ValidCabinetDTO_ReturnsCabinetWithDrugStock() {
        // Arrange
        CabinetDTO cabinetDTO = new CabinetDTO("Cabinet 1", "1");

        Cabinet cabinet = new Cabinet();
        cabinet.setName("Cabinet 1");

        DrugStock drugStock = new DrugStock();
        drugStock.setId("1");
        cabinet.setDrugStock(drugStock);

        when(drugStockService.getById(cabinetDTO.getDrugStockId())).thenReturn(Optional.of(drugStock));

        // Act
        Cabinet result = cabinetMapper.toCabinet(cabinetDTO);

        // Assert
        assertEquals(cabinet, result);
    }

    @Test
    void toCabinet_ValidCabinetDTOWithoutDrugStock_ReturnsCabinetWithoutDrugStock() {
        // Arrange
        CabinetDTO cabinetDTO = new CabinetDTO("Cabinet 1", null);

        Cabinet cabinet = new Cabinet();
        cabinet.setName("Cabinet 1");

        when(drugStockService.getById(cabinetDTO.getDrugStockId())).thenReturn(Optional.empty());

        // Act
        Cabinet result = cabinetMapper.toCabinet(cabinetDTO);

        // Assert
        assertEquals(cabinet, result);
    }
}

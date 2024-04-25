package com.example.MyVet.DrugSupplier;

import org.junit.Assert;
import org.junit.Test;

public class DrugSupplierMapperTest {

    @Test
    public void testToDrugSupplier() {
        String supplierName = "Supplier A";
        DrugSupplierDTO drugSupplierDTO = new DrugSupplierDTO();
        drugSupplierDTO.setName(supplierName);

        DrugSupplierMapper drugSupplierMapper = new DrugSupplierMapper();
        DrugSupplier drugSupplier = drugSupplierMapper.toDrugSupplier(drugSupplierDTO);

        Assert.assertNotNull(drugSupplier);
        Assert.assertEquals(supplierName, drugSupplier.getName());
    }
}

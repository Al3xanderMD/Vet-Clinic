package com.example.MyVet.UserCart.Receipt;

import com.example.MyVet.Drug.DrugService;
import com.example.MyVet.UserCart.Receipt.DrugSold.DrugSold;
import com.example.MyVet.UserCart.Receipt.DrugSold.DrugSoldService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class ReceiptMapper {
    private static DrugSoldService drugSoldService;

    private static double price = 0;
    @Autowired
    private ReceiptMapper(DrugSoldService drugSoldService){
        this.drugSoldService = drugSoldService;
    }

    public static Receipt toReceipt(ReceiptDTO receiptDTO){
        Receipt receipt = new Receipt();

        var lista = new ArrayList<DrugSold>();

        for (var id: receiptDTO.getDrugsSoldId())
        {
            var drugsS = drugSoldService.getById(id);
            drugsS.ifPresent(drugs -> lista.add(drugs));
            price = price + drugSoldService.getById(id).get().getPrice();

        }
        receipt.setDrugSoldList(lista);
        receipt.setPriceReceipt(price);
        price = 0;
        return receipt;
    }
}

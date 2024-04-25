package com.example.MyVet.UserCart;

import com.example.MyVet.Pet.Pet;
import com.example.MyVet.User.users.UserService;
import com.example.MyVet.UserCart.Receipt.Receipt;
import com.example.MyVet.UserCart.Receipt.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class UserCartMapper {

    private static UserService userService;
    private static ReceiptService receiptService;

    @Autowired
    public UserCartMapper(UserService userService, ReceiptService receiptService){
        this.userService = userService;
        this.receiptService = receiptService;
    }

    public static UserCart toUserCart(UserCartDTO userCartDTO){
        UserCart userCart = new UserCart();

        var userC = userService.getById(userCartDTO.getUserId());
        userC.ifPresent(userCart::setUser);

        var lista = new ArrayList<Receipt>();

        for (var id: userCartDTO.getReceiptsId())
        {
            var receipts = receiptService.getById(id);
            receipts.ifPresent(rec -> lista.add(rec));
        }
        userCart.setReceiptList(lista);

        return userCart;
    }
}

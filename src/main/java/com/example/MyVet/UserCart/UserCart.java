package com.example.MyVet.UserCart;

import com.example.MyVet.User.users.User;
import com.example.MyVet.UserCart.Receipt.Receipt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection =  "user_cart")
public class UserCart {
    @Id
    private String id;

    @DBRef
    private User user;

    @DBRef
    private List<Receipt> receiptList;

    public UserCart(User user) {
        this.user = user;
    }
}

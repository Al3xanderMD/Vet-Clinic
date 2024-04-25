package com.example.MyVet.UserCart;

import com.example.MyVet.User.users.User;
import com.example.MyVet.User.users.UserRepository;
import com.example.MyVet.User.users.UserService;
import com.example.MyVet.UserCart.Receipt.Receipt;
import com.example.MyVet.UserCart.Receipt.ReceiptService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserCartMapperTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserService userService = new UserService(userRepository);
    @Mock
    private ReceiptService receiptService;
    @Mock
    private UserCartMapper userCartMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userCartMapper = new UserCartMapper(userService, receiptService);
    }

    @Test
    public void testToUserCart() {
        // Arrange
        UserCartDTO userCartDTO = new UserCartDTO();
        userCartDTO.setUserId("123");
        List<String> receiptsId = List.of("456", "789");
        userCartDTO.setReceiptsId(receiptsId);

        UserCart userCart = new UserCart();
        User user = new User();
        user.setId("123");
        userCart.setUser(user);
        List<Receipt> receiptList = new ArrayList<>();
        Receipt receipt1 = new Receipt();
        receipt1.setId("456");
        Receipt receipt2 = new Receipt();
        receipt2.setId("789");
        receiptList.add(receipt1);
        receiptList.add(receipt2);
        userCart.setReceiptList(receiptList);

        Mockito.when(userService.getById("123")).thenReturn(Optional.of(user));
        Mockito.when(receiptService.getById("456")).thenReturn(Optional.of(receipt1));
        Mockito.when(receiptService.getById("789")).thenReturn(Optional.of(receipt2));

        // Act
        UserCart result = userCartMapper.toUserCart(userCartDTO);

        // Assert
        Assertions.assertEquals(userCart, result);
    }

    @Test
    public void testConstructor() {
        userCartMapper = new UserCartMapper();
        // Assert
        Assertions.assertNotNull(userCartMapper);
    }
//    @Test
//    public void testAutowiredConstructor() {
//        // Arrange
//        UserCartMapper userCartMapper = new UserCartMapper(userService, receiptService);
//
//        // Assert
//        Assertions.assertNotNull(userCartMapper);
//        Assertions.assertEquals(userService, userCartMapper.);
//        Assertions.assertEquals(receiptService, userCartMapper.getReceiptService());
//    }

}

package com.example.MyVet.UserCart;

import com.example.MyVet.User.users.User;
import com.example.MyVet.UserCart.Receipt.Receipt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

public class UserCartTest {

    @Mock
    private User mockUser;

    @Mock
    private Receipt mockReceipt;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNoArgsConstructor() {
        UserCart userCart = new UserCart();

        Assertions.assertNull(userCart.getId());
        Assertions.assertNull(userCart.getUser());
        Assertions.assertNull(userCart.getReceiptList());
    }

    @Test
    public void testAllArgsConstructor() {
        String id = "12345";
        List<Receipt> receiptList = new ArrayList<>();
        UserCart userCart = new UserCart(id, mockUser, receiptList);

        Assertions.assertEquals(id, userCart.getId());
        Assertions.assertEquals(mockUser, userCart.getUser());
        Assertions.assertEquals(receiptList, userCart.getReceiptList());
    }

    @Test
    public void testConstructorWithUser() {
        UserCart userCart = new UserCart(mockUser);

        Assertions.assertNull(userCart.getId());
        Assertions.assertEquals(mockUser, userCart.getUser());
        Assertions.assertNull(userCart.getReceiptList());
    }

    @Test
    public void testIdAnnotation() throws NoSuchFieldException {
        Id idAnnotation = UserCart.class.getDeclaredField("id").getAnnotation(Id.class);

        Assertions.assertNotNull(idAnnotation);
    }

    @Test
    public void testDBRefAnnotationForUser() throws NoSuchFieldException {
        DBRef dbRefAnnotation = UserCart.class.getDeclaredField("user").getAnnotation(DBRef.class);

        Assertions.assertNotNull(dbRefAnnotation);
    }

    @Test
    public void testDBRefAnnotationForReceiptList() throws NoSuchFieldException {
        DBRef dbRefAnnotation = UserCart.class.getDeclaredField("receiptList").getAnnotation(DBRef.class);

        Assertions.assertNotNull(dbRefAnnotation);
    }

    @Test
    public void testDocumentAnnotation() {
        Document documentAnnotation = UserCart.class.getAnnotation(Document.class);

        Assertions.assertNotNull(documentAnnotation);
        Assertions.assertEquals("user_cart", documentAnnotation.collection());
    }

    @Test
    public void testSetterAndGetterForId() {
        String id = "12345";
        UserCart userCart = new UserCart();
        userCart.setId(id);

        Assertions.assertEquals(id, userCart.getId());
    }

    @Test
    public void testSetterAndGetterForUser() {
        UserCart userCart = new UserCart();
        userCart.setUser(mockUser);

        Assertions.assertEquals(mockUser, userCart.getUser());
    }

    @Test
    public void testSetterAndGetterForReceiptList() {
        List<Receipt> receiptList = new ArrayList<>();
        receiptList.add(mockReceipt);

        UserCart userCart = new UserCart();
        userCart.setReceiptList(receiptList);

        Assertions.assertEquals(receiptList, userCart.getReceiptList());
    }

    @Test
    public void testToString() {
        String userId = "12345";
        User user = new User();
        user.setId(userId);
        List<Receipt> receiptList = new ArrayList<>();
        receiptList.add(new Receipt());
        receiptList.add(new Receipt());

        UserCart userCart = new UserCart(user);
        userCart.setId("cartId");
        userCart.setReceiptList(receiptList);

        String expectedToString = "UserCart(id=cartId, user=User(id=12345, firstName=null, lastName=null, email=null, phone=null, password=null, icn=null, adresses=null, role=null, verificationCode=null, enabled=false), receiptList=[Receipt(id=null, drugSoldList=null, priceReceipt=0.0, date=2023-05-24T21:45:55.761998100), Receipt(id=null, drugSoldList=null, priceReceipt=0.0, date=2023-05-24T21:45:55.763992900)])";

        Assertions.assertNotEquals(expectedToString, userCart.toString());
    }

    @Test
    public void testHashCode() {
        String userId = "12345";
        User user = new User();
        user.setId(userId);
        List<Receipt> receiptList = new ArrayList<>();
        receiptList.add(new Receipt());
        receiptList.add(new Receipt());

        UserCart userCart1 = new UserCart(user);
        userCart1.setId("cartId");
        userCart1.setReceiptList(receiptList);

        UserCart userCart2 = new UserCart(user);
        userCart2.setId("cartId");
        userCart2.setReceiptList(receiptList);

        Assertions.assertEquals(userCart1.hashCode(), userCart2.hashCode());
    }
}

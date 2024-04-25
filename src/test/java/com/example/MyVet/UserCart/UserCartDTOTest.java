package com.example.MyVet.UserCart;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class UserCartDTOTest {

    @Test
    public void testNoArgsConstructor() {
        UserCartDTO userCartDTO = new UserCartDTO();

        Assertions.assertNull(userCartDTO.getUserId());
        Assertions.assertEquals(new ArrayList<>(), userCartDTO.getReceiptsId());
    }

    @Test
    public void testAllArgsConstructor() {
        String userId = "12345";
        List<String> receiptsId = new ArrayList<>();
        receiptsId.add("receiptId1");
        receiptsId.add("receiptId2");
        UserCartDTO userCartDTO = new UserCartDTO(userId, receiptsId);

        Assertions.assertEquals(userId, userCartDTO.getUserId());
        Assertions.assertEquals(receiptsId, userCartDTO.getReceiptsId());
    }

//    @Test
//    public void testBlankUserId() {
//        String userId = "";
//        List<String> receiptsId = new ArrayList<>();
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            new UserCartDTO(userId, receiptsId);
//        });
//    }
//
//    @Test
//    public void testBlankReceiptsId() {
//        String userId = "12345";
//        List<String> receiptsId = new ArrayList<>();
//        receiptsId.add("");
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            new UserCartDTO(userId, receiptsId);
//        });
//    }
//
//    @Test
//    public void testNotBlankAnnotationForUserId() throws NoSuchFieldException {
//        NotBlank notBlankAnnotation = UserCartDTO.class.getDeclaredField("userId").getAnnotation(NotBlank.class);
//
//        Assertions.assertNotNull(notBlankAnnotation);
//    }
//
//    @Test
//    public void testNotBlankAnnotationForReceiptsId() throws NoSuchFieldException {
//        NotBlank notBlankAnnotation = UserCartDTO.class.getDeclaredField("receiptsId").getAnnotation(NotBlank.class);
//
//        Assertions.assertNotNull(notBlankAnnotation);
//    }
    @Test
    public void testSetterAndGetterForUserId() {
        String userId = "12345";
        UserCartDTO userCartDTO = new UserCartDTO();
        userCartDTO.setUserId(userId);

        Assertions.assertEquals(userId, userCartDTO.getUserId());
    }

    @Test
    public void testSetterAndGetterForReceiptsId() {
        List<String> receiptsId = new ArrayList<>();
        receiptsId.add("receiptId1");
        receiptsId.add("receiptId2");

        UserCartDTO userCartDTO = new UserCartDTO();
        userCartDTO.setReceiptsId(receiptsId);

        Assertions.assertEquals(receiptsId, userCartDTO.getReceiptsId());
    }

    @Test
    public void testEquals() {
        String userId = "12345";
        List<String> receiptsId = new ArrayList<>();
        receiptsId.add("receiptId1");
        receiptsId.add("receiptId2");

        UserCartDTO userCartDTO1 = new UserCartDTO(userId, receiptsId);
        UserCartDTO userCartDTO2 = new UserCartDTO(userId, receiptsId);
        UserCartDTO userCartDTO3 = new UserCartDTO("67890", receiptsId);

        Assertions.assertEquals(userCartDTO1, userCartDTO2);
        Assertions.assertNotEquals(userCartDTO1, userCartDTO3);
    }

    @Test
    public void testToString() {
        String userId = "12345";
        List<String> receiptsId = new ArrayList<>();
        receiptsId.add("receiptId1");
        receiptsId.add("receiptId2");

        UserCartDTO userCartDTO = new UserCartDTO(userId, receiptsId);
        String expectedToString = "UserCartDTO(userId=12345, receiptsId=[receiptId1, receiptId2])";

        Assertions.assertEquals(expectedToString, userCartDTO.toString());
    }

    @Test
    public void testCanEqual() {
        String userId = "12345";
        List<String> receiptsId = new ArrayList<>();
        receiptsId.add("receiptId1");
        receiptsId.add("receiptId2");

        UserCartDTO userCartDTO1 = new UserCartDTO(userId, receiptsId);
        UserCartDTO userCartDTO2 = new UserCartDTO(userId, receiptsId);

        Assertions.assertTrue(userCartDTO1.canEqual(userCartDTO2));
    }

    @Test
    public void testHashCode() {
        String userId = "12345";
        List<String> receiptsId = new ArrayList<>();
        receiptsId.add("receiptId1");
        receiptsId.add("receiptId2");

        UserCartDTO userCartDTO1 = new UserCartDTO(userId, receiptsId);
        UserCartDTO userCartDTO2 = new UserCartDTO(userId, receiptsId);

        Assertions.assertEquals(userCartDTO1.hashCode(), userCartDTO2.hashCode());
    }
}

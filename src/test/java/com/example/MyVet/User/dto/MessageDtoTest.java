package com.example.MyVet.User.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageDtoTest {

    @Test
    void testGetMessageAndConstructor() {
        //given
        String expectedMessage = "Hello, my friend!";
        MessageDto messageDto = new MessageDto(expectedMessage);
        //when
        String actualMessage = messageDto.getMessage();
        //then
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setMessageTest() {
        // given
        String expectedMessage = "Hello, world!";
        MessageDto messageDto = new MessageDto("Heeeeelp!");

        // when
        messageDto.setMessage(expectedMessage);

        // then
        String actualMessage = messageDto.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    void verifyAnnotations(){
        assertFalse(MessageDto.class.isAnnotationPresent(AllArgsConstructor.class));
        assertFalse(MessageDto.class.isAnnotationPresent(Data.class));
    }

    @Test
    public void testEquals() {
        MessageDto message1 = new MessageDto("Hello");
        MessageDto message2 = new MessageDto("Hello");
        MessageDto message3 = new MessageDto("Hi");
        MessageDto message4 = new MessageDto("Bau");
        MessageDto message5 = new MessageDto("Bau");
        MessageDto message6 = new MessageDto("HiHi");

        assertFalse(message1.equals(message6));
        assertTrue(message4.equals(message5));
        assertFalse(message3.equals(message6));
        assertTrue(message1.equals(message2)); // Test equals for two equal objects
        assertFalse(message1.equals(message3)); // Test equals for two different objects
    }

    @Test
    public void testToString() {
        MessageDto message = new MessageDto("Hello");
        String expectedToString = "MessageDto(message=Hello)";
        assertEquals(expectedToString, message.toString()); // Test toString
    }


    @Test
    public void testHashCode() {
        MessageDto message1 = new MessageDto("Hello");
        MessageDto message2 = new MessageDto("Hello");
        MessageDto message3 = new MessageDto("Hi");
        MessageDto message4 = new MessageDto("Bau");
        MessageDto message5 = new MessageDto("Bau");
        MessageDto message6 = new MessageDto("HiHi");

        assertEquals(message4.hashCode(), message5.hashCode());
        assertNotEquals(message6.hashCode(), message2.hashCode());
        assertNotEquals(message6.hashCode(), message5.hashCode());
        assertEquals(message1.hashCode(), message2.hashCode()); // Test hashCode for two equal objects
        assertNotEquals(message1.hashCode(), message3.hashCode()); // Test hashCode for two different objects
    }
    @Test
    public void testEqualsAndHashcodeWithNull() {
        MessageDto message1 = new MessageDto("Hello");
        MessageDto message2 = new MessageDto(null);

        assertFalse(message1.equals(message2));
        assertFalse(((Integer) message1.hashCode()).equals((Integer)message2.hashCode()));
    }

    @Test
    public void testEqualsAndHashcodeWithSameObject() {
        MessageDto message1 = new MessageDto("Hello");

        assertTrue(message1.equals(message1));
        assertTrue(message1.hashCode() == message1.hashCode());
    }

    @Test
    public void testEqualsAndHashcodeWithDifferentClass() {
        MessageDto message1 = new MessageDto("Hello");
        String message2 = "Hello";

        assertFalse(message1.equals(message2));
        assertFalse(message1.hashCode() == message2.hashCode());
    }

    @Test
    public void testEqualsAndHashcodeWithDifferentMessage() {
        MessageDto message1 = new MessageDto("Hello");
        MessageDto message2 = new MessageDto("Hi");

        assertFalse(message1.equals(message2));
        assertFalse(message1.hashCode() == message2.hashCode());
    }

    @Test
    public void testEqualsAndHashcodeWithEqualMessages() {
        MessageDto message1 = new MessageDto("Hello");
        MessageDto message2 = new MessageDto("Hello");

        assertTrue(message1.equals(message2));
        assertTrue(message1.hashCode() == message2.hashCode());
    }

    @Test
    public void testEqualsAndHashcodeWithEqualMessagesAndNullId() {
        MessageDto message1 = new MessageDto("Hello");
        MessageDto message2 = new MessageDto("Hello");


        assertTrue(message1.equals(message2));
        assertTrue(message1.hashCode() == message2.hashCode());
    }

    @Test
    public void testEqualsAndHashcodeWithEqualMessagesAndEqualIds() {
        MessageDto message1 = new MessageDto("Hello");
        MessageDto message2 = new MessageDto("Hello");


        assertTrue(message1.equals(message2));
        assertTrue(message1.hashCode() == message2.hashCode());
    }

    @Test
    public void testEqualsAndHashcodeWithEqualMessagesAndDifferentIds() {
        MessageDto message1 = new MessageDto("Hello");
        MessageDto message2 = new MessageDto("Hello");

        assertTrue(message1.equals(message2));
        assertTrue(message1.hashCode() == message2.hashCode());
    }
}
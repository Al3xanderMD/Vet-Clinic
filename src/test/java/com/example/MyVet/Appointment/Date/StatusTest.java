package com.example.MyVet.Appointment.Date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusTest {

    @Test
    public void testEnumValues() {
        // Verify the values of the enum
        assertEquals(Status.passed, Status.valueOf("passed"));
        assertEquals(Status.mine, Status.valueOf("mine"));
        assertEquals(Status.taken, Status.valueOf("taken"));
        assertEquals(Status.available, Status.valueOf("available"));
    }

    @Test
    public void testEnumToString() {
        // Verify the toString() method of the enum
        assertEquals("passed", Status.passed.toString());
        assertEquals("mine", Status.mine.toString());
        assertEquals("taken", Status.taken.toString());
        assertEquals("available", Status.available.toString());
    }
}

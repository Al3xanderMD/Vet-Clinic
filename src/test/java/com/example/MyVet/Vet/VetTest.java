package com.example.MyVet.Vet;

import com.example.MyVet.User.users.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import com.example.MyVet.MedicalStaff.MedicalStaff;

public class VetTest {
    @Test
    public void testConstructorWithMedicalStaff() {
        MedicalStaff medicalStaff = new MedicalStaff();
        Vet vet = new Vet(medicalStaff);

        assertNull(vet.getId());
        assertEquals(medicalStaff, vet.getMedicalStaff());
    }

    @Test
    public void testNoArgsConstructor() {
        Vet vet = new Vet();

        assertNull(vet.getId());
        assertEquals(null, vet.getMedicalStaff());
    }

    @Test
    public void testSetterAndGetters() {
        MedicalStaff medicalStaff = new MedicalStaff();
        Vet vet = new Vet();

        vet.setId("1");
        vet.setMedicalStaff(medicalStaff);

        assertEquals("1", vet.getId());
        assertEquals(medicalStaff, vet.getMedicalStaff());
    }

    @Test
    public void testEqualsAndHashCode() {
        MedicalStaff medicalStaff1 = new MedicalStaff();
        MedicalStaff medicalStaff2 = new MedicalStaff();

        Vet vet1 = new Vet(medicalStaff1);
        vet1.setId("1");

        Vet vet2 = new Vet(medicalStaff1);
        vet2.setId("1");

        Vet vet3 = new Vet(medicalStaff2);
        vet3.setId("2");

        // Equals
        assertEquals(vet1, vet2);
        assertNotEquals(vet1, vet3);

        // HashCode
        assertEquals(vet1.hashCode(), vet2.hashCode());
        assertNotEquals(vet1.hashCode(), vet3.hashCode());
    }
    @Test
    public void testAllArgsConstructor() {
        MedicalStaff medicalStaff = new MedicalStaff(new User("name", "lname", "email", "1111111111","123345", "bau", "edfgh"));
        Vet vet = new Vet("1", medicalStaff);

        assertEquals("1", vet.getId());
        assertEquals(medicalStaff, vet.getMedicalStaff());
    }

    @Test
    public void testToString() {
        MedicalStaff medicalStaff = new MedicalStaff(new User("name", "lname", "email", "1111111111","123345", "bau", "edfgh"));
        Vet vet = new Vet("1", medicalStaff);

        String expectedToString = "Vet(id=1, medicalStaff=MedicalStaff(id=null, schedule=null, salary=0, experience=0," +
                " user=User(id=null, firstName=name, lastName=lname, email=email, phone=1111111111, password=edfgh, icn=123345," +
                " adresses=[bau], role=null, verificationCode=null, enabled=false), cabinet=null))";
        assertEquals(expectedToString, vet.toString());
    }
}


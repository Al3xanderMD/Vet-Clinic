package com.example.MyVet.Appointment;

import com.example.MyVet.Pet.Pet;
import com.example.MyVet.PetOwner.PetOwner;
import com.example.MyVet.Vet.Vet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

class AppointmentTest {
    private Appointment appointment;
    private Appointment appointment1;
    private Appointment appointment2;
    private Appointment appointment3;

    @BeforeEach
    void setUp() {
        appointment = new Appointment();
        appointment1 = new Appointment("1", new Pet(), new PetOwner(), new Vet(), 2023, 5, 25, 10, 30);
        appointment2 = new Appointment("1", new Pet(), new PetOwner(), new Vet(), 2023, 5, 25, 10, 30);
        appointment3 = new Appointment("2", new Pet(), new PetOwner(), new Vet(), 2023, 5, 25, 10, 30);
    }

    @Test
    void idFieldTest() {
        appointment.setId("123");
        Assertions.assertEquals("123", appointment.getId(), "Getter or setter for 'id' field is not working correctly.");
    }

    @Test
    void petFieldTest() {
        Pet pet = new Pet();
        appointment.setPet(pet);
        Assertions.assertEquals(pet, appointment.getPet(), "Getter or setter for 'pet' field is not working correctly.");
    }

    @Test
    void petOwnerFieldTest() {
        PetOwner petOwner = new PetOwner();
        appointment.setPetOwner(petOwner);
        Assertions.assertEquals(petOwner, appointment.getPetOwner(), "Getter or setter for 'petOwner' field is not working correctly.");
    }

    @Test
    void vetFieldTest() {
        Vet vet = new Vet();
        appointment.setVet(vet);
        Assertions.assertEquals(vet, appointment.getVet(), "Getter or setter for 'vet' field is not working correctly.");
    }

    @Test
    void yearFieldTest() {
        appointment.setYear(2023);
        Assertions.assertEquals(2023, appointment.getYear(), "Getter or setter for 'year' field is not working correctly.");
    }

    @Test
    void monthFieldTest() {
        appointment.setMonth(5);
        Assertions.assertEquals(5, appointment.getMonth(), "Getter or setter for 'month' field is not working correctly.");
    }

    @Test
    void dayFieldTest() {
        appointment.setDay(25);
        Assertions.assertEquals(25, appointment.getDay(), "Getter or setter for 'day' field is not working correctly.");
    }

    @Test
    void hourFieldTest() {
        appointment.setHour(10);
        Assertions.assertEquals(10, appointment.getHour(), "Getter or setter for 'hour' field is not working correctly.");
    }

    @Test
    void minuteFieldTest() {
        appointment.setMinute(30);
        Assertions.assertEquals(30, appointment.getMinute(), "Getter or setter for 'minute' field is not working correctly.");
    }

    @Test
    void documentAnnotationTest() {
        boolean hasDocumentAnnotation = Appointment.class.isAnnotationPresent(Document.class);
        if (hasDocumentAnnotation) {
            Document documentAnnotation = Appointment.class.getAnnotation(Document.class);
            String collectionName = documentAnnotation.collection();
            Assertions.assertEquals("appointment", collectionName, "Incorrect collection name in @Document annotation of Appointment class.");
        } else {
            Assertions.fail("@Document annotation is missing in Appointment class.");
        }
    }

    @Test
    void idAnnotationTest() throws NoSuchFieldException {
        boolean hasIdAnnotation = Appointment.class.getDeclaredField("id").isAnnotationPresent(Id.class);
        Assertions.assertTrue(hasIdAnnotation, "@Id annotation is missing in 'id' field of Appointment class.");
    }

    @Test
    void dbRefAnnotationsTest() throws NoSuchFieldException {
        boolean hasPetAnnotation = Appointment.class.getDeclaredField("pet").isAnnotationPresent(DBRef.class);
        boolean hasPetOwnerAnnotation = Appointment.class.getDeclaredField("petOwner").isAnnotationPresent(DBRef.class);
        boolean hasVetAnnotation = Appointment.class.getDeclaredField("vet").isAnnotationPresent(DBRef.class);

        Assertions.assertTrue(hasPetAnnotation, "@DBRef annotation is missing in 'pet' field of Appointment class.");
        Assertions.assertTrue(hasPetOwnerAnnotation, "@DBRef annotation is missing in 'petOwner' field of Appointment class.");
        Assertions.assertTrue(hasVetAnnotation, "@DBRef annotation is missing in 'vet' field of Appointment class.");
    }
    @Test
    void equalsTest() {
        Assertions.assertEquals(appointment1, appointment2, "equals() method does not return true for equal appointments.");
        Assertions.assertNotEquals(appointment1, appointment3, "equals() method returns true for different appointments.");
    }

    @Test
    void hashCodeTest() {
        Assertions.assertEquals(appointment1.hashCode(), appointment2.hashCode(), "hashCode() method does not return the same value for equal appointments.");
        Assertions.assertNotEquals(appointment1.hashCode(), appointment3.hashCode(), "hashCode() method returns the same value for different appointments.");
    }

    @Test
    void canEqualTest() {
        Assertions.assertTrue(appointment1.canEqual(appointment2), "canEqual() method does not return true for the same class.");
        Assertions.assertFalse(appointment1.canEqual(new Object()), "canEqual() method returns true for different class.");
    }

    @Test
    void toStringTest() {
        String expectedString = "Appointment(id=1, pet=Pet(id=null, imageURL=null, hasPassport=null, name=null, species=null, race=null, UIN=0, healthStatus=null, medicalHistoryList=null), petOwner=PetOwner(id=null, petList=null, user=null), vet=Vet(id=null, medicalStaff=null), year=2023, month=5, day=25, hour=10, minute=30)";
        Assertions.assertEquals(expectedString, appointment1.toString(), "toString() method does not return the expected string representation.");
    }

    @Test
    void constructorsTest() {
        Appointment appointment = new Appointment("1", new Pet(), new PetOwner(), new Vet(), 2023, 5, 25, 10, 30);

        Assertions.assertEquals("1", appointment.getId(), "Constructor does not set the ID correctly.");
        Assertions.assertNotNull(appointment.getPet(), "Constructor does not initialize the 'pet' field.");
        Assertions.assertNotNull(appointment.getPetOwner(), "Constructor does not initialize the 'petOwner' field.");
        Assertions.assertNotNull(appointment.getVet(), "Constructor does not initialize the 'vet' field.");
        Assertions.assertEquals(2023, appointment.getYear(), "Constructor does not set the 'year' field correctly.");
        Assertions.assertEquals(5, appointment.getMonth(), "Constructor does not set the 'month' field correctly.");
        Assertions.assertEquals(25, appointment.getDay(), "Constructor does not set the 'day' field correctly.");
        Assertions.assertEquals(10, appointment.getHour(), "Constructor does not set the 'hour' field correctly.");
        Assertions.assertEquals(30, appointment.getMinute(), "Constructor does not set the 'minute' field correctly.");
    }
}

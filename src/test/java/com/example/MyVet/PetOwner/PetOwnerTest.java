package com.example.MyVet.PetOwner;

import com.example.MyVet.Pet.MedicalHistory.MedicalHistory;
import com.example.MyVet.Pet.Pet;
import com.example.MyVet.User.users.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.assertions.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.*;

public class PetOwnerTest {

    @Test
    public void testConstructorWithUser() {
        // Create a User object
        User user = new User("fname", "lname", "email", "12345678", "2w345678", "bau", "swdefrgthyj");



        // Create a PetOwner object using the constructor with User parameter
        PetOwner petOwner = new PetOwner(user);

        // Verify that the user is set correctly
        assertEquals(user, petOwner.getUser());
        assertNull(petOwner.getPetList());
    }

    @Test
    public void testEmptyConstructor() {
        // Create a PetOwner object using the empty constructor
        PetOwner petOwner = new PetOwner();

        // Verify that the petList and user fields are initialized
        assertNull(petOwner.getPetList());
        assertNull(petOwner.getUser());
    }

    @Test
    public void testGetterAndSetter() {
        // Create a PetOwner object
        PetOwner petOwner = new PetOwner();

        // Create a list of Pet objects
        List<Pet> petList = new ArrayList<>();
        String id = "petId";
        String imageURL = "image.jpg";
        Boolean hasPassport = true;
        String name = "Max";
        String species = "Dog";
        String race = "Labrador";
        int UIN = 123456;
        String healthStatus = "Healthy";

        Pet pet = new Pet(id, imageURL, hasPassport, name, species, race, UIN, healthStatus, List.of(new MedicalHistory()));
        Pet pet1 = new Pet("id", "image1.jpg", false,"bau", "Cat", "Buristish Short Hair", 12345, "asa si asa", List.of(new MedicalHistory()));
        petList.add(pet);
        petList.add(pet1);

        // Set the petList using the setter
        petOwner.setPetList(petList);

        // Create a User object
        User user = new User("fname", "lname", "email2", "12345678", "2w345678", "bau", "swdefrgthyj");

        // Set the user using the setter
        petOwner.setUser(user);

        // Verify that the petList and user are set correctly
        assertEquals(petList, petOwner.getPetList());
        assertEquals(user, petOwner.getUser());
    }
    @Test
    public void testHashCode() {
        // Create PetOwner objects with identical values
        PetOwner petOwner1 = new PetOwner();
        PetOwner petOwner2 = new PetOwner();

        assertEquals(petOwner1.hashCode(), petOwner2.hashCode());

        // Modify one field in petOwner1
        User user = new User("fname", "lname", "email", "12345678", "2w345678", "bau", "swdefrgthyj");
        petOwner1.setUser(user);

        assertNotEquals(petOwner1.hashCode(), petOwner2.hashCode());
    }

    @Test
    public void testToString() {
        // Create a PetOwner object
        PetOwner petOwner = new PetOwner();

        // Create a list of Pet objects
        List<Pet> petList = new ArrayList<>();
        petList.add(new Pet());
        petList.add(new Pet());

        // Set the petList using the setter
        petOwner.setPetList(petList);

        // Create a User object
        User user = new User("fname", "lname", "email", "12345678", "2w345678", "bau", "swdefrgthyj");

        // Set the user using the setter
        petOwner.setUser(user);

        String expected = "PetOwner(id=null, petList=[Pet(id=null, imageURL=null, hasPassport=null, name=null, species=null, race=null, UIN=0, healthStatus=null, medicalHistoryList=null), Pet(id=null, imageURL=null, hasPassport=null, name=null, species=null, race=null, UIN=0, healthStatus=null, medicalHistoryList=null)], user=User(id=null, firstName=fname, lastName=lname, email=email, phone=12345678, password=swdefrgthyj, icn=2w345678, adresses=[bau], role=null, verificationCode=null, enabled=false))";
        assertEquals(expected, petOwner.toString());
    }
    @Test
    public void testConstructorWithUser3() {
        // Create a User object
        User user = new User("fname", "lname", "email3", "12345678", "2w345678", "bau", "swdefrgthyj");

        // Create a PetOwner object using the constructor with User parameter
        PetOwner petOwner = new PetOwner(user);

        // Verify that the user is set correctly
        assertEquals(user, petOwner.getUser());
        assertNull(petOwner.getPetList());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Create two PetOwner objects with the same values
        List<Pet> petList1 = new ArrayList<>();
        petList1.add(new Pet("id", "image1.jpg", false,"baulica", "Cat", "Buristish Short Hair", 12345, "asa si asa", List.of(new MedicalHistory())));
        petList1.add(new Pet("id", "image1.jpg", false,"bau", "Cat", "Buristish Short Hair", 12345, "asa si asa", List.of(new MedicalHistory())));
        User user1 = new User("fname", "lname", "email", "12345678", "2w345678", "bau", "swdefrgthyj");

        List<Pet> petList2 = new ArrayList<>();
        petList2.add(new Pet("id", "image1.jpg", false,"bau", "Dog", "Buristish Short Hair", 12345, "asa si asa", List.of(new MedicalHistory())));
        petList2.add(new Pet("id", "image1.jpg", false,"bau", "Dog", "Buristish Short Hair", 12345, "asa si asa", List.of(new MedicalHistory())));
        User user2 = new User("fname", "lname", "email", "12345678", "2w345678", "bau2", "swdefrgthyj");

        PetOwner petOwner1 = new PetOwner("id",petList1, user1);
        PetOwner petOwner2 = new PetOwner("id1",petList2, user2);

        // Verify that the objects are equal and have the same hash code
        assertFalse(petOwner1.equals(petOwner2));
        assertNotEquals(petOwner1.hashCode(), petOwner2.hashCode());
    }

    @Test
    public void testEqualsWithDifferentObject() {
        // Create a PetOwner object
        List<Pet> petList = new ArrayList<>();
        petList.add(new Pet("id", "image1.jpg", false,"bau", "Cat", "Buristish Short Hair", 12345, "asa si asa", List.of(new MedicalHistory())));
        petList.add(new Pet("id", "image1.jpg", false,"bau", "Dog", "Buristish Short Hair", 12345, "asa si asa", List.of(new MedicalHistory())));
        User user = new User("fname", "lname", "email", "12345678", "2w345678", "bau", "swdefrgthyj");
        PetOwner petOwner = new PetOwner("id",petList, user);

        // Verify that equals returns false when comparing with a different object type
        assertFalse(petOwner.equals("Not a PetOwner"));
    }

    @Test
    public void testEqualsWithNullFields() {
        // Create two PetOwner objects with null fields
        PetOwner petOwner1 = new PetOwner(null,null, null);
        PetOwner petOwner2 = new PetOwner(null, null,null);

        // Verify that the objects are equal
        assertTrue(petOwner1.equals(petOwner2));
        assertTrue(petOwner2.equals(petOwner1));
        assertEquals(petOwner1.hashCode(), petOwner2.hashCode());
    }

    @Test
    public void testCanEqual() {
        // Create two PetOwner objects with the same values
        List<Pet> petList1 = new ArrayList<>();
        petList1.add(new Pet("id", "image1.jpg", false,"bau", "Cat", "Buristish Short Hair", 12345, "asa si asa", List.of(new MedicalHistory())));
        petList1.add(new Pet("id", "image1.jpg", false,"bau", "Dog", "Buristish Short Hair", 12345, "asa si asa", List.of(new MedicalHistory())));
        User user1 = new User("fname", "lname", "email", "12345678", "2w345678", "bau", "swdefrgthyj");

        List<Pet> petList2 = new ArrayList<>();
        petList2.add(new Pet("id", "image1.jpg", false,"bau", "Cat", "Buristish Short Hair", 12345, "asa si asa", List.of(new MedicalHistory())));
        petList2.add(new Pet("id", "image1.jpg", false,"bau", "Cat", "Buristish Short Hair", 12345, "asa si asa", List.of(new MedicalHistory())));
        User user2 = new User("fname", "lname", "email", "12345678", "2w345678", "bau", "swdefrgthyj");

        PetOwner petOwner1 = new PetOwner("id1",petList1, user1);
        PetOwner petOwner2 = new PetOwner("id2",petList2, user2);

        // Verify that canEqual returns true when comparing with the same object type
        assertTrue(petOwner1.canEqual(petOwner2));
        assertTrue(petOwner2.canEqual(petOwner1));
    }

    @Test
    public void testCanEqualWithDifferentObject() {
        // Create a PetOwner object
        List<Pet> petList = new ArrayList<>();
        petList.add(new Pet("id", "image1.jpg", false,"bau", "Cat", "Buristish Short Hair", 12345, "asa si asa", List.of(new MedicalHistory())));
        petList.add(new Pet("id", "image1.jpg", false,"bau", "Dog", "Buristish Short Hair", 12345, "asa si asa", List.of(new MedicalHistory())));
        User user = new User("fname", "lname", "email", "12345678", "2w345678", "bau", "swdefrgthyj");
        PetOwner petOwner = new PetOwner("id",petList, user);

        // Verify that canEqual returns false when comparing with a different object type
        assertFalse(petOwner.canEqual("Not a PetOwner"));
    }
    @Test
    public void testEquals() {
        // Arrange
        User user1 = new User("fname", "lname", "email3", "12345678", "2w345678", "bau", "swdefrgthyj");
        PetOwner petOwner1 = new PetOwner(user1);
        PetOwner petOwner2 = new PetOwner(user1);
        PetOwner petOwner3 = new PetOwner(new User("fname", "lname", "email4", "12345678", "2w345678", "bau", "swdefrgthyj"));

        // Act
        boolean equals1 = petOwner1.equals(petOwner2);
        boolean equals2 = petOwner1.equals(petOwner3);

        // Assert
        Assertions.assertTrue(equals1);
        Assertions.assertFalse(equals2);
    }

    @Test
    public void testSetId() {
        // Arrange
        PetOwner petOwner = new PetOwner(new User("fname", "lname", "email3", "12345678", "2w345678", "bau", "swdefrgthyj"));

        // Act
        petOwner.setId("123456");

        // Assert
        Assertions.assertEquals("123456", petOwner.getId());
    }
}

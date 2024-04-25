package com.example.MyVet.Pet;

import com.example.MyVet.Pet.MedicalHistory.MedicalHistory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.example.MyVet.PetOwner.PetOwner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    @Test
    void testConstructorAndGetters() {
        String id = "petId";
        String imageURL = "image.jpg";
        Boolean hasPassport = true;
        String name = "Max";
        String species = "Dog";
        String race = "Labrador";
        int UIN = 123456;
        String healthStatus = "Healthy";

        Pet pet = new Pet(id, imageURL, hasPassport, name, species, race, UIN, healthStatus, List.of(new MedicalHistory()));

        assertEquals(id, pet.getId());
        assertEquals(imageURL, pet.getImageURL());
        assertEquals(hasPassport, pet.getHasPassport());
        assertEquals(name, pet.getName());
        assertEquals(species, pet.getSpecies());
        assertEquals(race, pet.getRace());
        assertEquals(UIN, pet.getUIN());
        assertEquals(healthStatus, pet.getHealthStatus());
    }

    @Test
    void testSetterAndGetters() {
        Pet pet = new Pet();

        String id = "petId";
        pet.setId(id);
        assertEquals(id, pet.getId());

        String imageURL = "image.jpg";
        pet.setImageURL(imageURL);
        assertEquals(imageURL, pet.getImageURL());

        Boolean hasPassport = true;
        pet.setHasPassport(hasPassport);
        assertEquals(hasPassport, pet.getHasPassport());

        String name = "Max";
        pet.setName(name);
        assertEquals(name, pet.getName());

        String species = "Dog";
        pet.setSpecies(species);
        assertEquals(species, pet.getSpecies());

        String race = "Labrador";
        pet.setRace(race);
        assertEquals(race, pet.getRace());

        int UIN = 123456;
        pet.setUIN(UIN);
        assertEquals(UIN, pet.getUIN());

        String healthStatus = "Healthy";
        pet.setHealthStatus(healthStatus);
        assertEquals(healthStatus, pet.getHealthStatus());
    }

    @Test
    void testEqualsAndHashCode() {
        Pet pet1 = new Pet("petId", "image.jpg", true, "Max", "Dog", "Labrador", 123456, "Healthy", List.of(new MedicalHistory()));
        Pet pet2 = new Pet("petId", "image.jpg", true, "Max", "Dog", "Labrador", 123456, "Healthy", List.of(new MedicalHistory()));
        Pet pet3 = new Pet("petId2", "image2.jpg", false, "Buddy", "Cat", "Persian", 654321,  "Sick", List.of(new MedicalHistory()));

        // Test equality
        assertTrue(pet1.equals(pet2));
        assertTrue(pet2.equals(pet1));
        assertEquals(pet1.hashCode(), pet2.hashCode());

        // Test inequality
        assertFalse(pet1.equals(pet3));
        assertFalse(pet3.equals(pet1));
    }

    @Test
    void testToString() {
        Pet pet = new Pet("petId", "image.jpg", true, "Max", "Dog", "Labrador", 123456,  "Healthy", List.of(new MedicalHistory()));

        String expectedToString = "Pet(id=petId, imageURL=image.jpg, hasPassport=true, name=Max, species=Dog, race=Labrador, UIN=123456, healthStatus=Healthy, medicalHistoryList=[MedicalHistory(id=null, diagnostics=null, drugList=null, date=2023-05-23T21:31:53.349819500)])";
        assertNotEquals(expectedToString, pet.toString());
    }
    @Test
    public void testNoArgsConstructor() {
        // Arrange
        Pet pet = new Pet();

        // Assert
        Assertions.assertNotNull(pet);
    }

    @Test
    public void testAllArgsConstructor() {
        // Arrange
        String imageURL = "https://example.com/pet.jpg";
        Boolean hasPassport = true;
        String name = "Max";
        String species = "Dog";
        String race = "Labrador Retriever";
        int UIN = 123456;
        String healthStatus = "Healthy";

        // Act
        Pet pet = new Pet(imageURL, hasPassport, name, species, race, UIN, healthStatus);

        // Assert
        Assertions.assertNotNull(pet);
        Assertions.assertEquals(imageURL, pet.getImageURL());
        Assertions.assertEquals(hasPassport, pet.getHasPassport());
        Assertions.assertEquals(name, pet.getName());
        Assertions.assertEquals(species, pet.getSpecies());
        Assertions.assertEquals(race, pet.getRace());
        Assertions.assertEquals(UIN, pet.getUIN());
        Assertions.assertEquals(healthStatus, pet.getHealthStatus());
    }

    @Test
    public void testSetters() {
        // Arrange
        Pet pet = new Pet();

        // Act
        String imageURL = "https://example.com/pet.jpg";
        pet.setImageURL(imageURL);
        Boolean hasPassport = true;
        pet.setHasPassport(hasPassport);
        String name = "Max";
        pet.setName(name);
        String species = "Dog";
        pet.setSpecies(species);
        String race = "Labrador Retriever";
        pet.setRace(race);
        int UIN = 123456;
        pet.setUIN(UIN);
        String healthStatus = "Healthy";
        pet.setHealthStatus(healthStatus);

        // Assert
        Assertions.assertEquals(imageURL, pet.getImageURL());
        Assertions.assertEquals(hasPassport, pet.getHasPassport());
        Assertions.assertEquals(name, pet.getName());
        Assertions.assertEquals(species, pet.getSpecies());
        Assertions.assertEquals(race, pet.getRace());
        Assertions.assertEquals(UIN, pet.getUIN());
        Assertions.assertEquals(healthStatus, pet.getHealthStatus());
    }
}

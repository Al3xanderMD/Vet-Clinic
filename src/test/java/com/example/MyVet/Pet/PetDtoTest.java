package com.example.MyVet.Pet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetDtoTest {

    private PetDto petDto1;
    private PetDto petDto2;
    private PetDto petDto3;

    @BeforeEach
    public void setUp() {
        petDto1 = new PetDto("Max","Dog", "Labrador", 123456789, "image.jpg", true, "Healthy", List.of("bau"));
        petDto2 = new PetDto("Charlie", "Cat", "Persian", 987654321, "image.png", false, "Sick", List.of("bau"));
        petDto3 = new PetDto("Max", "Dog", "Labrador", 123456789, "image.jpg", true, "Healthy", List.of("bau"));
    }

    @Test
    public void testEquals() {
        assertEquals(petDto1, petDto1); // Reflexive
        assertEquals(petDto1, petDto3); // Same field values
        assertEquals(petDto3, petDto1); // Same field values
        Assertions.assertNotEquals(petDto1, petDto2); // Different field values
        Assertions.assertNotEquals(petDto1, null); // Not equal to null
        Assertions.assertNotEquals(petDto1, new Object()); // Not equal to different class
    }

    @Test
    public void testCanEqual() {
        Assertions.assertTrue(petDto1.canEqual(petDto1));
        Assertions.assertTrue(petDto1.canEqual(petDto3));
    }

    @Test
    public void testHashCode() {
        assertEquals(petDto1.hashCode(), petDto3.hashCode());
    }

    @Test
    public void testToString() {
        String expectedToString ="PetDto(name=Max, species=Dog, race=Labrador, UIN=123456789, imageURL=image.jpg, hasPassport=true, healthStatus=Healthy, medicalHistories=[bau])";
        assertEquals(expectedToString, petDto1.toString());
    }
    @Test
    public void testEquals1() {
        // Test reflexivity
        assertEquals(petDto1, petDto1);

        // Test equality with same field values
        assertEquals(petDto1, petDto3);
        assertEquals(petDto3, petDto1);

        // Test inequality with different field values
        Assertions.assertNotEquals(petDto1, petDto2);
        Assertions.assertNotEquals(petDto2, petDto1);

        // Test inequality with null and different class
        Assertions.assertNotEquals(petDto1, null);
        Assertions.assertNotEquals(petDto1, new Object());
    }

    @Test
    public void testCanEqual1() {
        // Test reflexivity
        Assertions.assertTrue(petDto1.canEqual(petDto1));

        // Test equality with same field values
        Assertions.assertTrue(petDto1.canEqual(petDto3));
    }

    @Test
    public void testHashCod1e() {
        // Test consistency
        assertEquals(petDto1.hashCode(), petDto1.hashCode());
        assertEquals(petDto1.hashCode(), petDto3.hashCode());

        // Test inequality with different field values
        Assertions.assertNotEquals(petDto1.hashCode(), petDto2.hashCode());
    }

    @Test
    public void testToString1() {
        String expectedToString = "PetDto(name=Max, species=Dog, race=Labrador, UIN=123456789, imageURL=image.jpg, hasPassport=true, healthStatus=Healthy, medicalHistories=[bau])";

        // Test toString output
        assertEquals(expectedToString, petDto1.toString());
    }

    @Test
    public void testSetName() {
        // Arrange
        PetDto petDto = new PetDto();

        // Act
        petDto.setName("Max");

        // Assert
        assertEquals("Max", petDto.getName());
    }

    @Test
    public void testSetSpecies() {
        // Arrange
        PetDto petDto = new PetDto();

        // Act
        petDto.setSpecies("Dog");

        // Assert
        assertEquals("Dog", petDto.getSpecies());
    }

    @Test
    public void testSetRace() {
        // Arrange
        PetDto petDto = new PetDto();

        // Act
        petDto.setRace("Labrador");

        // Assert
        assertEquals("Labrador", petDto.getRace());
    }

    @Test
    public void testSetUIN() {
        // Arrange
        PetDto petDto = new PetDto();

        // Act
        petDto.setUIN(12345);

        // Assert
        assertEquals(12345, petDto.getUIN());
    }

    @Test
    public void testSetImageURL() {
        // Arrange
        PetDto petDto = new PetDto();

        // Act
        petDto.setImageURL("https://example.com/image.jpg");

        // Assert
        assertEquals("https://example.com/image.jpg", petDto.getImageURL());
    }

    @Test
    public void testSetHasPassport() {
        // Arrange
        PetDto petDto = new PetDto();

        // Act
        petDto.setHasPassport(true);

        // Assert
        assertEquals(true, petDto.getHasPassport());
    }

    @Test
    public void testSetHealthStatus() {
        // Arrange
        PetDto petDto = new PetDto();

        // Act
        petDto.setHealthStatus("Healthy");

        // Assert
        assertEquals("Healthy", petDto.getHealthStatus());
    }
    @Test
    public void testHashCode2() {
        // Arrange
        PetDto petDto1 = new PetDto("Max", "Dog", "Labrador Retriever", 123456, "https://example.com/pet.jpg", true, "Healthy", Arrays.asList("Medical History 1", "Medical History 2"));
        PetDto petDto2 = new PetDto("Max", "Dog", "Labrador Retriever", 123456, "https://example.com/pet.jpg", true, "Healthy", Arrays.asList("Medical History 1", "Medical History 2"));

        // Act
        int hashCode1 = petDto1.hashCode();
        int hashCode2 = petDto2.hashCode();

        // Assert
        Assertions.assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void testEquals2() {
        // Arrange
        PetDto petDto1 = new PetDto("Max", "Dog", "Labrador Retriever", 123456, "https://example.com/pet.jpg", true, "Healthy", Arrays.asList("Medical History 1", "Medical History 2"));
        PetDto petDto2 = new PetDto("Max", "Dog", "Labrador Retriever", 123456, "https://example.com/pet.jpg", true, "Healthy", Arrays.asList("Medical History 1", "Medical History 2"));
        PetDto petDto3 = new PetDto("Buddy", "Cat", "Persian", 789012, "https://example.com/cat.jpg", false, "Sick", Arrays.asList("Medical History 3", "Medical History 4"));

        // Act
        boolean equals1 = petDto1.equals(petDto2);
        boolean equals2 = petDto1.equals(petDto3);

        // Assert
        Assertions.assertTrue(equals1);
        Assertions.assertFalse(equals2);
    }

    @Test
    public void testSetters() {
        // Arrange
        PetDto petDto = new PetDto();

        // Act
        petDto.setName("Max");
        petDto.setSpecies("Dog");
        petDto.setRace("Labrador Retriever");
        petDto.setUIN(123456);
        petDto.setImageURL("https://example.com/pet.jpg");
        petDto.setHasPassport(true);
        petDto.setHealthStatus("Healthy");
        petDto.setMedicalHistories(Arrays.asList("Medical History 1", "Medical History 2"));

        // Assert
        Assertions.assertEquals("Max", petDto.getName());
        Assertions.assertEquals("Dog", petDto.getSpecies());
        Assertions.assertEquals("Labrador Retriever", petDto.getRace());
        Assertions.assertEquals(123456, petDto.getUIN());
        Assertions.assertEquals("https://example.com/pet.jpg", petDto.getImageURL());
        Assertions.assertTrue(petDto.getHasPassport());
        Assertions.assertEquals("Healthy", petDto.getHealthStatus());
        Assertions.assertEquals(Arrays.asList("Medical History 1", "Medical History 2"), petDto.getMedicalHistories());
    }
}

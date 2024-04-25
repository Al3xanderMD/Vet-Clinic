package com.example.MyVet.Assistant;

import static org.junit.jupiter.api.Assertions.*;

import com.example.MyVet.MedicalStaff.MedicalStaff;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "assistant")
public class AssistantTest {

    @Test
    public void testConstructorWithMedicalStaff() {
        MedicalStaff medicalStaff = new MedicalStaff();
        Assistant assistant = new Assistant(medicalStaff);

        assertNull(assistant.getId());
        assertEquals(medicalStaff, assistant.getMedicalStaff());
    }

    @Test
    public void testConstructorWithIdAndMedicalStaff() {
        String id = "123";
        MedicalStaff medicalStaff = new MedicalStaff();
        Assistant assistant = new Assistant(id, medicalStaff);

        assertEquals(id, assistant.getId());
        assertEquals(medicalStaff, assistant.getMedicalStaff());
    }

    @Test
    public void testDefaultConstructor() {
        Assistant assistant = new Assistant();

        assertNull(assistant.getId());
        assertNull(assistant.getMedicalStaff());
    }

    @Test
    public void testGettersAndSetters() {
        MedicalStaff medicalStaff = new MedicalStaff();
        Assistant assistant = new Assistant();

        assistant.setId("123");
        assistant.setMedicalStaff(medicalStaff);

        assertEquals("123", assistant.getId());
        assertEquals(medicalStaff, assistant.getMedicalStaff());
    }

    @Test
    public void testEqualsAndHashCode() {
        MedicalStaff medicalStaff1 = new MedicalStaff();
        MedicalStaff medicalStaff2 = new MedicalStaff();

        Assistant assistant1 = new Assistant();
        assistant1.setId("123");
        assistant1.setMedicalStaff(medicalStaff1);

        Assistant assistant2 = new Assistant();
        assistant2.setId("123");
        assistant2.setMedicalStaff(medicalStaff2);

        assertEquals(assistant1, assistant2);
        assertEquals(assistant1.hashCode(), assistant2.hashCode());
    }
    @Test
    public void testToString() {
        // Arrange
        String id = "123456";
        MedicalStaff medicalStaff = new MedicalStaff();

        Assistant assistant = new Assistant(id, medicalStaff);

        // Act
        String result = assistant.toString();

        // Assert
        String expected = "Assistant(id=123456, medicalStaff=MedicalStaff(id=null, schedule=null, salary=0, experience=0, user=null, cabinet=null))";
        Assertions.assertEquals(expected, result);
    }
}

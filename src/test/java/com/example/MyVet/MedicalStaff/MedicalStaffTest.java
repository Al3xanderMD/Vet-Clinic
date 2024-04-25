package com.example.MyVet.MedicalStaff;

import com.example.MyVet.Cabinet.Cabinet;
import com.example.MyVet.User.users.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class MedicalStaffTest {

    @Test
    public void testConstructorWithUser() {
        User user = new User("firstname","lastname","email","28282","777889","address", "password");
        MedicalStaff medicalStaff = new MedicalStaff(user);

        assertEquals(user, medicalStaff.getUser());
    }

    @Test
    public void testGettersAndSetters() {
        User user = new User("firstname","lastname","email","28282","777889","address", "password");
        Cabinet cabinet = new Cabinet();
        MedicalStaff medicalStaff = new MedicalStaff();

        medicalStaff.setId("123");
        medicalStaff.setSchedule("Monday-Friday");
        medicalStaff.setSalary(5000);
        medicalStaff.setExperience(5);
        medicalStaff.setUser(user);
        medicalStaff.setCabinet(cabinet);

        assertEquals("123", medicalStaff.getId());
        assertEquals("Monday-Friday", medicalStaff.getSchedule());
        assertEquals(5000, medicalStaff.getSalary());
        assertEquals(5, medicalStaff.getExperience());
        assertEquals(user, medicalStaff.getUser());
        assertEquals(cabinet, medicalStaff.getCabinet());
    }

    @Test
    public void testEqualsAndHashCode() {
        User user1 = new User("firstname","lastname","email","28282","777889","address", "password");
        User user2 = new User("firstname","lastname","email","28282","777889","address", "password");
        Cabinet cabinet1 = new Cabinet();
        Cabinet cabinet2 = new Cabinet();

        MedicalStaff medicalStaff1 = new MedicalStaff();
        medicalStaff1.setId("123");
        medicalStaff1.setSchedule("Monday-Friday");
        medicalStaff1.setSalary(5000);
        medicalStaff1.setExperience(5);
        medicalStaff1.setUser(user1);
        medicalStaff1.setCabinet(cabinet1);

        MedicalStaff medicalStaff2 = new MedicalStaff();
        medicalStaff2.setId("123");
        medicalStaff2.setSchedule("Monday-Friday");
        medicalStaff2.setSalary(5000);
        medicalStaff2.setExperience(5);
        medicalStaff2.setUser(user2);
        medicalStaff2.setCabinet(cabinet2);

        assertEquals(medicalStaff1, medicalStaff2);
        assertEquals(medicalStaff1.hashCode(), medicalStaff2.hashCode());
    }
    @Test
    public void testConstructorWithStringParams() {
        String id = "123";
        String schedule = "Monday-Friday";
        int salary = 5000;
        int experience = 5;
        User user = new User("firstname","lastname","email","28282","777889","address", "password");
        Cabinet cabinet = new Cabinet();

        MedicalStaff medicalStaff = new MedicalStaff(id, schedule, salary, experience, user, cabinet);

        assertEquals(id, medicalStaff.getId());
        assertEquals(schedule, medicalStaff.getSchedule());
        assertEquals(salary, medicalStaff.getSalary());
        assertEquals(experience, medicalStaff.getExperience());
        assertEquals(user, medicalStaff.getUser());
        assertEquals(cabinet, medicalStaff.getCabinet());
    }
}
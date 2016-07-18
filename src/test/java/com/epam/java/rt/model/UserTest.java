package com.epam.java.rt.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * simuseco
 */
public class UserTest {

    @Test
    public void initTest() {
        // creating and initiating verify
        User user = new User();
        assertNotNull(user);
        // setting data verify
        user.setId();
        user.setLogin("p1a-7ck");
        user.setPasswordHash(); // default password 1234567890
        user.setActive(true);
        user.setFirstName("Rollan");
        user.setLastName("Taigulov");
        user.setMiddleName("Maratovich");
        user.setPhoneNumber(PhoneNumber.of("8-712-2-554433", "-"));
        assertEquals("p1a-7ck", user.getLogin());
        assertTrue(user.verifyPassword("1234567890"));
        assertEquals(true, user.isActive());
        assertEquals("Rollan", user.getFirstName());
        assertEquals("Taigulov", user.getLastName());
        assertEquals("Maratovich", user.getMiddleName());
        assertEquals("8-712-2-55-44-33", user.getPhoneNumber().getCombined());
        // password setting verify
        user.setPassword("1234567890", "newPassword");
        assertTrue(user.verifyPassword("newPassword"));
        user.setPassword("1234567890", "extraPassword");
        assertFalse(user.verifyPassword("extraPassword"));
        user.setSalt(); // after salt is initiated or set by setPassword any setting salt should be ignored
        user.setSalt(new byte[]{10, 66, 34});
        user.setPasswordHash(); // after passwordHash is initiated or set by setPassword any setting passwordHash should be ignored
        user.setPasswordHash(new byte[]{10, 66, 34});
        assertTrue(user.verifyPassword("newPassword"));
        // name setting verify
        user.setFirstName("First");
        user.setLastName("Last");
        user.setMiddleName("Middle");
        assertEquals("First Middle Last", user.getFullName());
    }
}
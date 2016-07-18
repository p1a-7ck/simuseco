package com.epam.java.rt.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * simuseco
 */
public class PhoneNumberTest {

    @Test
    public void initTest() {
        // creating and initialization verify
        PhoneNumber phoneNumber;
        phoneNumber = new PhoneNumber(8, 712, 2, 554433, ".");
        assertNotNull(phoneNumber);
        phoneNumber = PhoneNumber.of("8.707.554.54.00", "."); // regex point escaping
        assertNotNull(phoneNumber);
        // initial data verify
        assertEquals(8, phoneNumber.getCountryCode());
        assertEquals(707, phoneNumber.getDefCode());
        assertEquals(-1, phoneNumber.getRegionCode());
        assertEquals(5545400, phoneNumber.getNumber());
        assertEquals(".", phoneNumber.getDelimiter());
        // combining verify
        assertEquals("8.707.554.54.00", phoneNumber.getCombined());
        phoneNumber.setDelimiter(" ");
        assertEquals(" ", phoneNumber.getDelimiter());
        assertEquals("8 707 554 54 00", phoneNumber.getCombined());
        // setting to temporary data and getting of set
        phoneNumber.setCountryCode(13);
        phoneNumber.setDefCode(421);
        phoneNumber.setRegionCode(5);
        phoneNumber.setNumber(778654);
        assertNotEquals(778654, phoneNumber.getNumber());
        assertEquals("8 707 554 54 00", phoneNumber.getCombined());
        phoneNumber = phoneNumber.copyOfSet();
        assertEquals("13 421 5 77 86 54", phoneNumber.getCombined());
    }

}
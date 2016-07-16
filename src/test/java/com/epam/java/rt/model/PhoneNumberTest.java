package com.epam.java.rt.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * simuseco
 */
public class PhoneNumberTest {

    @Test
    public void initTest() {
        // creating and initialization verify
        PhoneNumber phoneNumber = PhoneNumber.of("8.707.554.54.00", "."); // regex point escaping
        assertNotNull(phoneNumber);
        // initial data verify
        assertEquals(phoneNumber.getCountryCode(), 8);
        assertEquals(phoneNumber.getDefCode(), 707);
        assertEquals(phoneNumber.getRegionCode(), -1);
        assertEquals(phoneNumber.getNumber(), 5545400);
        assertEquals(phoneNumber.getDelimiter(), ".");
        // combining verify
        assertEquals(phoneNumber.getCombined(), "8.707.554.54.00");
        phoneNumber.setDelimiter(" ");
        assertEquals(phoneNumber.getDelimiter(), " ");
        assertEquals(phoneNumber.getCombined(), "8 707 554 54 00");
        // setting to temporary data and getting of set
        phoneNumber.setCountryCode(13);
        phoneNumber.setDefCode(421);
        phoneNumber.setRegionCode(5);
        phoneNumber.setNumber(778654);
        assertEquals(phoneNumber.getCombined(), "8 707 554 54 00");
        phoneNumber = phoneNumber.copyOfSet();
        assertEquals(phoneNumber.getCombined(), "13 421 5 77 86 54");
    }

}
package com.epam.java.rt.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * simuseco
 */
public class AddressTest {

    @Test
    public void initTest() {
        // initiating verify
        Address address;
        address = new Address("Country", "Area", "City", "Region", "Street", "Building", "Apartment", ", ");
        assertNotNull(address);
        address = Address.of("Kazakhstan, Akmolinskaya, Astana, Esil, Orynbor, 12, 30", ", ");
        assertNotNull(address);
        // getting verify
        assertEquals("Kazakhstan", address.getCountry());
        assertEquals("Akmolinskaya", address.getArea());
        assertEquals("Astana", address.getCity());
        assertEquals("Esil", address.getRegion());
        assertEquals("Orynbor", address.getStreet());
        assertEquals("12", address.getBuilding());
        assertEquals("30", address.getApartment());
        // combining verify
        assertEquals("Kazakhstan, Akmolinskaya, Astana, Esil, Orynbor, 12, 30", address.getCombined());
        address.setDelimiter(".");
        assertEquals(address.getDelimiter(), ".");
        assertEquals("Kazakhstan.Akmolinskaya.Astana.Esil.Orynbor.12.30", address.getCombined());
        // setting data verify
        address.setCountry("Russia");
        address.setArea("Krasnodarskii");
        address.setCity("Krasnodar");
        address.setRegion("Uzhnyi");
        address.setStreet("Pavlova");
        address.setBuilding("100");
        address.setApartment("33");
        address.setDelimiter(", ");
        assertNotEquals(address.getCountry(), "Russia");
        address = address.copyOfSet();
        assertEquals(address.getCountry(), "Russia");
        assertEquals(address.getArea(), "Krasnodarskii");
        assertEquals(address.getCity(), "Krasnodar");
        assertEquals(address.getRegion(), "Uzhnyi");
        assertEquals(address.getStreet(), "Pavlova");
        assertEquals(address.getBuilding(), "100");
        assertEquals(address.getApartment(), "33");
    }

}
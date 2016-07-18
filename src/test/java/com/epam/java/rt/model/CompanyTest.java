package com.epam.java.rt.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * simuseco
 */
public class CompanyTest {

    @Test
    public void initTest() {
        // initiating verify
        Company company = new Company();
        assertNotNull(company);
        // setting values verify
        company.setId();
        company.setName("BI-Service");
        company.setAddress(Address.of("Kazakhstan, Karagandinskay, Karaganda, Center, Mira, 31, 12", ", "));
        company.setPhoneNumber(PhoneNumber.of("8-712-2-78-88-90", "-"));
        assertEquals(company.getName(), "BI-Service");
        assertEquals(company.getAddress().getCombined(), "Kazakhstan, Karagandinskay, Karaganda, Center, Mira, 31, 12");
        assertEquals(company.getPhoneNumber().getCombined(), "8-712-2-78-88-90");
        // linking to user
        User user = new User();
        user.setId();
        user.setLogin("manager");
        user.setFirstName("Manager");
        company.setUserId(user.getId());
        assertEquals(company.getUserId(), user.getId());
    }
}
package com.epam.java.rt;

import com.epam.java.rt.model.PhoneNumber;
import com.epam.java.rt.model.User;

/**
 * simuseco
 */
public class Main {

    public static void main(String[] args) {

        User user = new User();

        user.setId();
        user.setFirstName("Rollan");
        user.setLastName("Taigulov");
        user.setMiddleName("Maratovich");
        user.setLogin("p1a-7ck");
        user.setPhoneNumber(PhoneNumber.of("8-707-554-54-00", "-"));

        System.out.println(PhoneNumber.of(user.getPhoneNumber().getCombined(), user.getPhoneNumber().getDelimiter()));

    }

}

package com.epam.java.rt.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * simuseco
 */
public class Company {
    private final static Logger COMPANY_LOG = LoggerFactory.getLogger(Company.class);
    private UUID id;
    private UUID userId;
    private String name;
    private Address address;
    private PhoneNumber phoneNumber;

    public Company() {
        COMPANY_LOG.info("Company constructed");
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        setId(UUID.randomUUID());
    }

    public void setId(UUID id) {
        this.id = id;
        COMPANY_LOG.info("Company id set to '{}'", this.id);
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

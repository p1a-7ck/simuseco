package com.epam.java.rt.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * simuseco
 */
public class Address {
    private final static Logger ADDRESS_LOG = LoggerFactory.getLogger(Address.class);
    public String delimiter = "";
    public final String country;
    public final String area;
    public final String city;
    public final String region;
    public final String street;
    public final String building;
    public final String apartment;
    String[] temporaryData = null;

    public Address(String country, String area, String city, String region, String street, String building, String apartment, String delimiter) {
        this.country = country;
        this.area = area;
        this.city = city;
        this.region = region;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.delimiter = delimiter;
        ADDRESS_LOG.info("Address constructed '{}'", this.getCombined());
    }

    private void initTemporaryData() {
        if (this.temporaryData == null || this.temporaryData.length == 0) {
            this.temporaryData = new String[7];
            this.temporaryData[0] = this.country;
            this.temporaryData[1] = this.area;
            this.temporaryData[2] = this.region;
            this.temporaryData[4] = this.street;
            this.temporaryData[5] = this.building;
            this.temporaryData[6] = this.apartment;
        }
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
        ADDRESS_LOG.info("Delimiter set to '{}'", this.delimiter);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        initTemporaryData();
        this.temporaryData[0] = country.trim();
        ADDRESS_LOG.info("Temporary country set to '{}'", this.temporaryData[0]);
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        initTemporaryData();
        this.temporaryData[1] = area.trim();
        ADDRESS_LOG.info("Temporary area set to '{}'", this.temporaryData[1]);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        initTemporaryData();
        this.temporaryData[2] = city.trim();
        ADDRESS_LOG.info("Temporary city set to '{}'", this.temporaryData[2]);
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        initTemporaryData();
        this.temporaryData[3] = region.trim();
        ADDRESS_LOG.info("Temporary region set to '{}'", this.temporaryData[3]);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        initTemporaryData();
        this.temporaryData[4] = street.trim();
        ADDRESS_LOG.info("Temporary street set to '{}'", this.temporaryData[4]);
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        initTemporaryData();
        this.temporaryData[5] = building.trim();
        ADDRESS_LOG.info("Temporary building set to '{}'", this.temporaryData[5]);
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        initTemporaryData();
        this.temporaryData[6] = apartment.trim();
        ADDRESS_LOG.info("Temporary apartment set to '{}'", this.temporaryData[6]);
    }

    public Address copyOfSet() {
        initTemporaryData();
        Address newAddress = new Address(this.temporaryData[0],
                this.temporaryData[1],
                this.temporaryData[2],
                this.temporaryData[3],
                this.temporaryData[4],
                this.temporaryData[5],
                this.temporaryData[6],
                this.delimiter);
        ADDRESS_LOG.info("Address copy set to '{}'", newAddress.getCombined());
        return newAddress;
    }

    public String getCombined() {
        StringBuilder result = new StringBuilder();
        if (this.country != null && this.country.length() > 0) result.append(this.country).append(this.delimiter);
        if (this.area != null && this.area.length() > 0) result.append(this.area).append(this.delimiter);
        if (this.city != null && this.city.length() > 0) result.append(this.city).append(this.delimiter);
        if (this.region != null && this.region.length() > 0) result.append(this.region).append(this.delimiter);
        if (this.street != null && this.street.length() > 0) result.append(this.street).append(this.delimiter);
        if (this.building != null && this.building.length() > 0) result.append(this.building).append(this.delimiter);
        if (this.apartment != null && this.apartment.length() > 0) result.append(this.apartment);
        return result.toString();
    }

    public static Address of(String address, String delimiter) {
        String[] addressParts;
        if (!delimiter.equals(".")) addressParts = address.split(delimiter, 7);
        else addressParts = address.split(new StringBuilder().append("\\").append(delimiter).toString(), 7);
        try {
            Address newAddress;
            newAddress = new Address(addressParts[0],
                    addressParts[1],
                    addressParts[2],
                    addressParts[3],
                    addressParts[4],
                    addressParts[5],
                    addressParts[6],
                    delimiter);
            ADDRESS_LOG.info("New address set to '{}'", newAddress.getCombined());
            return newAddress;
        } catch (ArrayIndexOutOfBoundsException exc) {
            ADDRESS_LOG.error("Address should contain seven parts delimited by '{}' ({})", delimiter, address, exc);
            return null;
        }
    }
}

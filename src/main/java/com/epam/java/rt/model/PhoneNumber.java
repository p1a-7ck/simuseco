package com.epam.java.rt.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * simuseco
 */
public final class PhoneNumber {
    private final static Logger PHONE_NUMBER_LOG = LoggerFactory.getLogger(PhoneNumber.class);
    public String delimiter = "";
    public final int countryCode;
    public final int defCode;
    public final int regionCode;
    public final int number;
    int[] temporaryData = null;

    public PhoneNumber(int countryCode, int defCode, int regionCode, int number, String delimiter) {
        this.countryCode = countryCode;
        this.defCode = defCode;
        this.regionCode = regionCode;
        this.number = number;
        this.delimiter = delimiter;
        PHONE_NUMBER_LOG.info("Phone number constructed '{}'", this.getCombined());
    }

    private void initTemporaryData() {
        if (this.temporaryData == null || this.temporaryData.length == 0) {
            this.temporaryData = new int[4];
            this.temporaryData[0] = this.countryCode;
            this.temporaryData[1] = this.defCode;
            this.temporaryData[2] = this.regionCode;
            this.temporaryData[3] = this.number;
        }
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
        PHONE_NUMBER_LOG.info("Delimiter set to '{}'", this.delimiter);
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        initTemporaryData();
        this.temporaryData[0] = countryCode;
        PHONE_NUMBER_LOG.info("Temporary country code set to '{}'", this.temporaryData[0]);
    }

    public int getDefCode() {
        return defCode;
    }

    public void setDefCode(int defCode) {
        initTemporaryData();
        this.temporaryData[1] = defCode;
        PHONE_NUMBER_LOG.info("Temporary def code set to '{}'", this.temporaryData[1]);
    }

    public int getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(int regionCode) {
        initTemporaryData();
        this.temporaryData[2] = regionCode;
        PHONE_NUMBER_LOG.info("Temporary region code set to '{}'", this.temporaryData[2]);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        initTemporaryData();
        this.temporaryData[3] = number;
        PHONE_NUMBER_LOG.info("Temporary number set to '{}'", this.temporaryData[3]);
    }

    public PhoneNumber copyOfSet() {
        initTemporaryData();
        PhoneNumber newPhoneNumber = new PhoneNumber(this.temporaryData[0],
                this.temporaryData[1],
                this.temporaryData[2],
                this.temporaryData[3],
                this.delimiter);
        PHONE_NUMBER_LOG.info("Phone number copy set to '{}'", newPhoneNumber.getCombined());
        return newPhoneNumber;
    }

    public String getCombined() {
        StringBuilder result = new StringBuilder();
        StringBuilder number = new StringBuilder();
        number.append(this.number);
        int numberFirstDelimiterIndex = 2;
        if (number.length() < 6) numberFirstDelimiterIndex = 1;
        if (number.length() > 6) numberFirstDelimiterIndex = 3;
        number.insert(numberFirstDelimiterIndex, this.delimiter);
        number.insert(numberFirstDelimiterIndex + 3, this.delimiter);
        if (this.countryCode >= 0) result.append(String.valueOf(this.countryCode)).append(this.delimiter);
        if (this.defCode >= 0) result.append(String.valueOf(this.defCode)).append(this.delimiter);
        if (this.regionCode >= 0) result.append(String.valueOf(this.regionCode)).append(this.delimiter);
        if (this.number >= 0) result.append(number);
        return result.toString();
    }

    public static PhoneNumber of(String phoneNumber, String delimiter) {
        String[] phoneNumberParts;
        if (!delimiter.equals(".")) phoneNumberParts = phoneNumber.split(delimiter, 4);
        else phoneNumberParts = phoneNumber.split(new StringBuilder().append("\\").append(delimiter).toString(), 4);
        phoneNumberParts[3] = phoneNumberParts[3].replace(delimiter, "");
        try {
            PhoneNumber newPhoneNumber;
            if (phoneNumberParts[2].length() > 2 && phoneNumberParts[3].length() < 5) {
                newPhoneNumber = new PhoneNumber(Integer.valueOf(phoneNumberParts[0]),
                        Integer.valueOf(phoneNumberParts[1]),
                        -1,
                        Integer.valueOf(phoneNumberParts[2] + phoneNumberParts[3]),
                        delimiter);
            } else {
                newPhoneNumber = new PhoneNumber(Integer.valueOf(phoneNumberParts[0]),
                        Integer.valueOf(phoneNumberParts[1]),
                        Integer.valueOf(phoneNumberParts[2]),
                        Integer.valueOf(phoneNumberParts[3]),
                        delimiter);
            }
            PHONE_NUMBER_LOG.info("New phone number set to '{}'", newPhoneNumber.getCombined());
            return newPhoneNumber;
        } catch (NumberFormatException exc) {
            PHONE_NUMBER_LOG.error("Wrong phone number format ({})", phoneNumber, exc);
            return null;
        } catch (ArrayIndexOutOfBoundsException exc) {
            PHONE_NUMBER_LOG.error("Phone number should contain four parts delimited by '{}' ({})", delimiter, phoneNumber, exc);
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumber that = (PhoneNumber) o;

        if (countryCode != that.countryCode) return false;
        if (defCode != that.defCode) return false;
        if (regionCode != that.regionCode) return false;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        int result = countryCode;
        result = 31 * result + defCode;
        result = 31 * result + regionCode;
        result = 31 * result + number;
        return result;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "countryCode=" + countryCode +
                ", defCode=" + defCode +
                ", regionCode=" + regionCode +
                ", number=" + number +
                ", delimiter='" + delimiter + "'" +
                '}';
    }
}
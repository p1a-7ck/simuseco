package com.epam.java.rt.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

/**
 * simuseco
 */
public class User extends BasePerson {
    private static final int ITERATIONS = 10000, KEY_LENGTH = 256;
    private final static Logger USER_LOG = LoggerFactory.getLogger(User.class);
    private final static Random random = new Random();
    private UUID id;
    private String login;
    private byte[] salt;
    private byte[] passwordHash;
    private boolean active;

    public User() {
        USER_LOG.info("User constructed");
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        setId(UUID.randomUUID());
    }

    public void setId(UUID id) {
        this.id = id;
        USER_LOG.info("User id set to '{}'", this.id);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login.trim();
        USER_LOG.info("User login set to '{}'", this.login);
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt() {
        setSalt(getNextSalt());
    }

    public void setSalt(byte[] salt) {
        if (this.salt == null || this.salt.length == 0) {
            this.salt = salt;
            USER_LOG.info("User salt set to '{}'", this.salt);
        }
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash() {
        setSalt();
        setPasswordHash(hashCalculate("1234567890".toCharArray(), this.salt));
    }

    public void setPasswordHash(byte[] passwordHash) {
        if (this.passwordHash == null || this.passwordHash.length == 0) {
            this.passwordHash = passwordHash;
            USER_LOG.info("User passwordHash set to '{}'", this.passwordHash);
        }
    }

    public boolean verifyPassword(String password) {
        if (hashCompare(password.toCharArray(), this.salt, this.passwordHash)) {
            USER_LOG.info("Verify pass succeed for '{}'", this.login);
            return true;
        } else {
            USER_LOG.info("Verify pass failed for '{}'", this.login);
            return false;
        }
    }

    public void setPassword(String oldPassword, String newPassword) {
        if (verifyPassword(oldPassword)) {
            this.salt = getNextSalt();
            this.passwordHash = hashCalculate(newPassword.toCharArray(), this.salt);
            USER_LOG.info("User passwordHash set to '{}'", this.passwordHash);
        } else {
            USER_LOG.info("User passwordHash failed for '{}'", this.login);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        USER_LOG.info("User active set to '{}'", this.active);
    }

    public static byte[] hashCalculate(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public static boolean hashCompare(char[] password, byte[] salt, byte[] expectedHash) {
        byte[] pwdHash = hashCalculate(password, salt);
        Arrays.fill(password, Character.MIN_VALUE);
        if (pwdHash.length != expectedHash.length) return false;
        for (int i = 0; i < pwdHash.length; i++) {
            if (pwdHash[i] != expectedHash[i]) return false;
        }
        return true;
    }

    public static byte[] getNextSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id +
                '}';
    }

    public String toInDetail() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", active=" + active +
                ", fullName='" + super.getFullName() + '\'' +
                ", phoneNumber='" + super.getPhoneNumber().getCombined() +
                '}';
    }
}
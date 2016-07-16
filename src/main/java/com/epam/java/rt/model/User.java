package com.epam.java.rt.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * simuseco
 */
public class User extends BasePerson {
    private final static Logger USER_LOG = LoggerFactory.getLogger(User.class);
    private UUID id;
    private String login;
    private String passHash;
    private boolean active;

    public User() {
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
        this.login = login;
        USER_LOG.info("User login set to '{}'", this.login);
    }

    public boolean verifyPass(String pass) {
        return true;
    }

    public void setPass(String oldPass, String newPass) {

        //USER_LOG.info("User id set to '{}'", this.id);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        USER_LOG.info("User active set to '{}'", this.active);
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
                ", passHash='" + passHash + '\'' +
                ", active=" + active +
                ", fullName='" + super.getFullName() + '\'' +
                ", phoneNumber='" + super.getPhoneNumber().getCombined() +
                '}';
    }
}
package com.example.jpademo.bd;

import java.io.Serializable;

public class BDProduct implements Serializable {

    private String name;
    private String email;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "BDProduct{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

package ru.mail.avdienkoartyom.model;

import java.util.Date;

public class Organization {
    private String name;

    public Organization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

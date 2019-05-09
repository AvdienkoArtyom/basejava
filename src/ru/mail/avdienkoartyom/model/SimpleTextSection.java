package ru.mail.avdienkoartyom.model;

import java.util.Objects;

public class SimpleTextSection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    private String description;

    public SimpleTextSection() {
    }

    public SimpleTextSection(String descriiption) {
        this.description = descriiption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleTextSection aboutMe = (SimpleTextSection) o;
        return Objects.equals(description, aboutMe.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
//

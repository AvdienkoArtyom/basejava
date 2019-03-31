package ru.mail.avdienkoartyom.model;

import java.util.Objects;

public class SympleTextSection extends AbstractSection {
    private String description;

    public SympleTextSection(String descriiption) {
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
        SympleTextSection aboutMe = (SympleTextSection) o;
        return Objects.equals(description, aboutMe.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
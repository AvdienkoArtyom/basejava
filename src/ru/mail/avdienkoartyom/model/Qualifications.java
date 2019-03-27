package ru.mail.avdienkoartyom.model;

import java.util.Objects;

public class Qualifications extends AbstractSection {
    private String string;

    public Qualifications(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Qualifications that = (Qualifications) o;
        return Objects.equals(string, that.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }
}

package ru.mail.avdienkoartyom.model;

import java.util.Objects;

public class Objective extends AbstractSection {
    private String descriiption;

    public Objective(String descriiption) {
        this.descriiption = descriiption;
    }

    public String getDescriiption() {
        return descriiption;
    }

    public void setDescriiption(String descriiption) {
        this.descriiption = descriiption;
    }

    @Override
    public String toString() {
        return descriiption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objective objective = (Objective) o;
        return Objects.equals(descriiption, objective.descriiption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriiption);
    }
}

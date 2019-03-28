package ru.mail.avdienkoartyom.model;

import java.util.List;
import java.util.Objects;

public class Qualification extends AbstractSection {
    private List<String> list;

    public Qualification(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s: list){
            stringBuilder.append(s + "\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Qualification that = (Qualification) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}

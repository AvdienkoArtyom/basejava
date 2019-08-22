package ru.mail.avdienkoartyom.model;


import ru.mail.avdienkoartyom.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final Position EMPTY = new Position();

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateStart;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateFinish;
    private String status;
    private String description;

    public Position() {
    }

    public Position(LocalDate dateStart, LocalDate dateFinish, String description) {
        this(dateStart, dateFinish, "", description);
    }

    public Position(LocalDate dateStart, LocalDate dateFinish, String status, String description) {
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.status = status;
        this.description = description == null ? "" : description;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateFinish() {
        return dateFinish;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateFinish(LocalDate dateFinish) {
        this.dateFinish = dateFinish;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(dateStart, position.dateStart) &&
                Objects.equals(dateFinish, position.dateFinish) &&
                Objects.equals(status, position.status) &&
                Objects.equals(description, position.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateStart, dateFinish, status, description);
    }
}

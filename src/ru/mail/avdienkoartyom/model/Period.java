package ru.mail.avdienkoartyom.model;


import ru.mail.avdienkoartyom.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Period implements Serializable {
    private static final long serialVersionUID = 1L;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateStart;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateFinish;
    private String status;
    private String description;

    public Period() {
    }

    public Period(LocalDate dateStart, LocalDate dateFinish, String description) {
        this(dateStart, dateFinish, "null", description);
    }

    public Period(LocalDate dateStart, LocalDate dateFinish, String status, String description) {
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.status = status;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(dateStart, period.dateStart) &&
                Objects.equals(dateFinish, period.dateFinish) &&
                Objects.equals(status, period.status) &&
                Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateStart, dateFinish, status, description);
    }
}

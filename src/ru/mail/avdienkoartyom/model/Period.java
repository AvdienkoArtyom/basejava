package ru.mail.avdienkoartyom.model;

import java.time.LocalDate;
import java.util.Objects;

public class Period {
    private LocalDate dateStart;
    private LocalDate dateFinish;
    private String status;
    private String description;

    public Period(LocalDate dateStart, LocalDate dateFinish, String description) {
        this(dateStart, dateFinish, "", description);
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

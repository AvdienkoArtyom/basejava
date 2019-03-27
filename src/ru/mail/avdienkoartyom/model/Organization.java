package ru.mail.avdienkoartyom.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private String title;
    private LocalDate dateStart;
    private LocalDate dateFinish;
    private String status;
    private String description;

    public Organization(String title, LocalDate dateStart, LocalDate dateFinish, String description) {
        this(title, dateStart, dateFinish, "", description);
    }

    public Organization(String title, LocalDate dateStart, LocalDate dateFinish, String status, String description) {
        this.title = title;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.status = status;
        this.description = description;

    }

    @Override
    public String toString() {
        if (status.isEmpty()) {
            return title + "\n" + " с " + dateStart + " по " + dateFinish + "\n" + description;
        } else {
            return title + "\n" + " с " + dateStart + " по " + dateFinish + "\n" + status + "\n" + description;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(dateStart, that.dateStart) &&
                Objects.equals(dateFinish, that.dateFinish) &&
                Objects.equals(status, that.status) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, dateStart, dateFinish, status, description);
    }
}

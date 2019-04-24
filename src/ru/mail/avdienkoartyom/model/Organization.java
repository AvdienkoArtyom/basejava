package ru.mail.avdienkoartyom.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;

    private List<Period> periodList;


    public Organization(String title, List<Period> periodList) {
        this.title = title;
        this.periodList = periodList;
    }

    public List<Period> getPeriodList() {
        return periodList;
    }

    public void setPeriodList(List<Period> periodList) {
        this.periodList = periodList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(title + "\n");

        for (Period p : periodList) {
            if (p.getStatus().isEmpty()) {
                sb.append(p.getDateStart() + " " + p.getDateFinish() + "\n" + p.getDescription() + "\n");
            } else {
                sb.append(p.getDateStart() + " " + p.getDateFinish() + "\n" + p.getStatus() + "\n" + p.getDescription() + "\n");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(periodList, that.periodList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, periodList);
    }
}

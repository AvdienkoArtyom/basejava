package ru.mail.avdienkoartyom.model;

import java.util.Date;

public class Experience extends AbstractStudyWorkPlace {
    private String positionAtWork;
    public Experience(Organization organizations,String positionAtWork, String status, String dateStartAndDateFinish) {
        super(organizations, status, dateStartAndDateFinish);
        this.positionAtWork = positionAtWork;
    }

    @Override
    public String toString() {
        return organizations + "\n" + dateStartAndDateFinish + " " + positionAtWork + "\n" + description;
    }
}

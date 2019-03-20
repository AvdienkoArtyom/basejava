package ru.mail.avdienkoartyom.model;

public class AbstractStudyWorkPlace extends AbstractSection {
    protected Organization organizations;
    protected String dateStartAndDateFinish;
    protected String description;

    public AbstractStudyWorkPlace(Organization organizations, String status, String dateStartAndDateFinish) {
        this.organizations = organizations;
        this.description = status;
        this.dateStartAndDateFinish = dateStartAndDateFinish;
    }

    public Organization getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Organization organizations) {
        this.organizations = organizations;
    }

    public String getDateStartAndDateFinish() {
        return dateStartAndDateFinish;
    }

    public void setDateStartAndDateFinish(String dateStartAndDateFinish) {
        this.dateStartAndDateFinish = dateStartAndDateFinish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return organizations + "\n" + dateStartAndDateFinish + " " + description;
    }
}

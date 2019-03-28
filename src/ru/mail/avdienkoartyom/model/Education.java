package ru.mail.avdienkoartyom.model;

import java.util.List;
import java.util.Objects;

public class Education extends AbstractSection {
    private List<Organization> organizationList;

    public Education(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Organization organization : organizationList) {
            sb.append(organization.toString() + "\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return Objects.equals(organizationList, education.organizationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationList);
    }
}

package ru.mail.avdienkoartyom.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Experience extends AbstractSection {

    private List<Organization> organizationList = new ArrayList<>();

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<Organization> organizationList) {
        this.organizationList = organizationList;
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
        Experience that = (Experience) o;
        return Objects.equals(organizationList, that.organizationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationList);
    }
}
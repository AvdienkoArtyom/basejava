package ru.mail.avdienkoartyom.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Organization> organizationList;

    public static OrganizationSection EMPTY = new OrganizationSection(Organization.EMPTY);

    public OrganizationSection() {
    }

    public OrganizationSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationSection(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

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
            sb.append(organization.toString());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(organizationList, that.organizationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationList);
    }
}

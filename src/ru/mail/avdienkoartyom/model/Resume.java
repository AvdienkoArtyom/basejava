package ru.mail.avdienkoartyom.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * Initial resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final Resume EMPTY = new Resume();

    static {
        EMPTY.setSection(SectionType.OBJECTIVE, SimpleTextSection.EMPTY);
        EMPTY.setSection(SectionType.PERSONAL, SimpleTextSection.EMPTY);
        EMPTY.setSection(SectionType.ACHIEVEMENT, ListSection.EMPTY);
        EMPTY.setSection(SectionType.QUALIFICATIONS, ListSection.EMPTY );
        EMPTY.setSection(SectionType.EXPERIENCE, new OrganizationSection(Organization.EMPTY));
        EMPTY.setSection(SectionType.EDUCATION, new OrganizationSection(Organization.EMPTY));
    }

    private String uuid;
    private String fullName;
    private Map<ContactType, String> contact = new EnumMap<>(ContactType.class);
    private Map<SectionType, AbstractSection> section = new EnumMap<>(SectionType.class);

    public Resume() {
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Map<SectionType, AbstractSection> getSection() {
        return section;
    }

    public AbstractSection getSection(SectionType type) {
        return section.get(type);
    }

    public void setSection(SectionType type, AbstractSection abstractSection) {
        section.put(type, abstractSection);
    }

    public Map<ContactType, String> getContact() {
        return contact;
    }

    public void setContact(Map<ContactType, String> contact) {
        this.contact = contact;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contact, resume.contact) &&
                Objects.equals(section, resume.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contact, section);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Полное имя: " + fullName + "\n");
        sb.append("Контакты: ");

        for (Map.Entry<ContactType, String> entry : contact.entrySet()) {
            sb.append(entry.getKey().getTitle() + ": " + entry.getValue() + "\n");
        }

        for (Map.Entry<SectionType, AbstractSection> entry : section.entrySet()) {
            sb.append(entry.getKey().getTitle() + ":\n" + entry.getValue() + "\n");
        }

        return new String(sb);
    }
}

package ru.mail.avdienkoartyom.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {
    private final String uuid;
    private final String fullName;
    private Map<Contact, String> contactStringMap = new EnumMap<>(Contact.class);
    private Map<SectionType, AbstractSection> sectionTypeAbstractSectionMap = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Map<SectionType, AbstractSection> getSectionTypeAbstractSectionMap() {
        return sectionTypeAbstractSectionMap;
    }

    public void setSectionTypeAbstractSectionMap(Map<SectionType, AbstractSection> sectionTypeAbstractSectionMap) {
        this.sectionTypeAbstractSectionMap = sectionTypeAbstractSectionMap;
    }

    public Map<Contact, String> getContactStringMap() {
        return contactStringMap;
    }

    public void setContactStringMap(Map<Contact, String> contactStringMap) {
        this.contactStringMap = contactStringMap;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }


    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.getFullName());
        return cmp != 1 ? cmp : uuid.compareTo(o.getUuid());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Полное имя: " + fullName + "\n");
        sb.append("Контакты: ");

        for (Map.Entry<Contact, String> entry : contactStringMap.entrySet()) {
            sb.append(entry.getKey().getTitle() + ": " + entry.getValue() + "\n");
        }

        for (Map.Entry<SectionType, AbstractSection> entry : sectionTypeAbstractSectionMap.entrySet()) {
            sb.append(entry.getKey().getTitle() + ": " + entry.getValue() + "\n");
        }

        return new String(sb);
    }
}

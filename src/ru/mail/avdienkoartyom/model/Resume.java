package ru.mail.avdienkoartyom.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {
    // Unique identifier
    private final String uuid;
    private final String fullName;
    private List<String> contact = new ArrayList<>();

    private Map<SectionType, List<AbstractSection>> map = new HashMap<>();

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Map<SectionType, List<AbstractSection>> getMap() {
        return map;
    }

    public void setMap(Map<SectionType, List<AbstractSection>> map) {
        this.map = map;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public List<String> getContact() {
        return contact;
    }

    public void setContact(List<String> contact) {
        this.contact = contact;
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
        for (String s : contact) {
            sb.append(s + "\n");
        }
        sb.append("Личные качества:\n" + map.get(SectionType.PERSONAL).get(0).toString() + "\n");
        sb.append("Позиция:\n" + map.get(SectionType.OBJECTIVE).get(0).toString() + "\n");

        sb.append("Достижения:\n");

        for (AbstractSection achievement : map.get(SectionType.ACHIEVEMENT)) {
            sb.append(achievement.toString() + "\n");
        }

        sb.append("Квалификация" + "\n");

        for (AbstractSection qualifications : map.get(SectionType.QUALIFICATIONS)) {
            sb.append(qualifications.toString() + "\n");
        }

        sb.append("Опыт работы");

        for (AbstractSection experiens : map.get(SectionType.EXPERIENCE)) {
            sb.append(experiens.toString() + "\n");
        }

        sb.append("Образование");

        for (AbstractSection education : map.get(SectionType.EDUCATION)) {
            sb.append(education.toString() + "\n");
        }

        return new String(sb);
    }
}

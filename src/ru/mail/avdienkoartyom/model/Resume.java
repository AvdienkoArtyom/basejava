package ru.mail.avdienkoartyom.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    private final String fullName;

    public Resume() {
        this(UUID.randomUUID().toString());
    }

    public Resume(String uuid, String... fullName) {
        this.uuid = uuid;
        int sizeFullName = fullName.length;
        if (sizeFullName > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < sizeFullName; i++) {
                stringBuffer.append(fullName[i] + " ");
            }

            this.fullName = stringBuffer.toString().trim();
            System.out.println("Full Name: " + this.fullName);
        } else {
            this.fullName = "No name";
        }

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
        return Objects.equals(uuid, resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return uuid + " " + fullName;
    }


    @Override
    public int compareTo(Resume o) {
        return fullName.compareTo(o.getFullName());
    }
}

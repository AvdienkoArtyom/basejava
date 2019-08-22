package ru.mail.avdienkoartyom.model;

public enum SectionType {
    OBJECTIVE("Позиция"),
    PERSONAL("Личные качества"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификации"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String title;

    public String getTitle() {
        return title;
    }

    SectionType(String title) {
        this.title = title;
    }
}

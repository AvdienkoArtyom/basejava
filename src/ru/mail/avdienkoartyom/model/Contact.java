package ru.mail.avdienkoartyom.model;

public enum Contact {
    TELEPHONE("Телефон"),
    SKYPE("Skype"),
    EMAIL("Почта"),
    PROFILE_LINKEDIN("Профиль LinkedIn"),
    PROFILE_GITHUB("Профиль GitHub"),
    PROFILE_STACKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    private String title;

    public String getTitle() {
        return title;
    }

    Contact(String title) {
        this.title = title;
    }
}

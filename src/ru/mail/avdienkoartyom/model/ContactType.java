package ru.mail.avdienkoartyom.model;

public enum ContactType {
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

    ContactType(String title) {
        this.title = title;
    }
}
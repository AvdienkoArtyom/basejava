package ru.mail.avdienkoartyom.model;

public enum ContactType {
    TELEPHONE("Телефон"),
    SKYPE("Skype"){
        @Override
        public String toHtml(String value) {
            return "<a href='skype:" + value + "'>" + value + "</a>";
        }
    },
    EMAIL("Почта"){
        @Override
        public String toHtml(String value) {
            return "<a href='mailto:" + value + "'>" + value + "</a>";
        }
    },
    PROFILE_LINKEDIN("Профиль LinkedIn"){
        @Override
        public String toHtml(String value) {
            return "<a href='Linkedin:" + value + "'>" + value + "</a>";
        }
    },
    PROFILE_GITHUB("Профиль GitHub"){
        @Override
        public String toHtml(String value) {
            return "<a href='GitHub:" + value + "'>" + value + "</a>";
        }
    },
    PROFILE_STACKOVERFLOW("Профиль Stackoverflow"){
        @Override
        public String toHtml(String value) {
            return "<a href='StackOverFlow:" + value + "'>" + value + "</a>";
        }
    },
    HOMEPAGE("Домашняя страница"){
        @Override
        public String toHtml(String value) {
            return "<a href='HomePage:" + value + "'>" + value + "</a>";
        }
    };

    private String title;

    public String getTitle() {
        return title;
    }

    ContactType(String title) {
        this.title = title;
    }

    public String toHtml(String value){
        return title + ": " + value;
    }

}

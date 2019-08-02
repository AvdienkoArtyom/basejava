package ru.mail.avdienkoartyom.model;

public enum ContactType {
    TELEPHONE("Телефон"),
    SKYPE("Skype") {
        @Override
        public String toHtml(String value) {
            return toLink(value);
        }
    },
    EMAIL("Почта") {
        @Override
        public String toHtml(String value) {
            return toLink(value);
        }
    },
    PROFILE_LINKEDIN("Профиль LinkedIn") {
        @Override
        public String toHtml(String value) {
            return toLink(value);
        }
    },
    PROFILE_GITHUB("Профиль GitHub") {
        @Override
        public String toHtml(String value) {
            return toLink(value);
        }
    },
    PROFILE_STACKOVERFLOW("Профиль Stackoverflow") {
        @Override
        public String toHtml(String value) {
            return toLink(value);
        }
    },
    HOMEPAGE("Домашняя страница") {
        @Override
        public String toHtml(String value) {
            return toLink(value);
        }
    };

    private String title;

    public String getTitle() {
        return title;
    }

    ContactType(String title) {
        this.title = title;
    }

    public String toHtml(String value) {
        return title + ": " + value;
    }


    public String toLink(String href) {
        return toLink(href, title);
    }

    public String toLink(String href, String value) {
        return value + ": <a href=" + href + ">" + href + "</a>";
    }

}

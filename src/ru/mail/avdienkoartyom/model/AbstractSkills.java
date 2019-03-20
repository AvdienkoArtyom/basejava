package ru.mail.avdienkoartyom.model;

import java.util.List;

public class AbstractSkills extends AbstractSection {
    protected String string;

    public AbstractSkills(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}

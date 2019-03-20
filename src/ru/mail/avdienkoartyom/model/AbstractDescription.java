package ru.mail.avdienkoartyom.model;

import java.util.ArrayList;
import java.util.List;

public class AbstractDescription extends AbstractSection {
    private String descriiption;

    public AbstractDescription(String descriiption) {
        this.descriiption = descriiption;
    }

    public String getDescriiption() {
        return descriiption;
    }

    public void setDescriiption(String descriiption) {
        this.descriiption = descriiption;
    }

    @Override
    public String toString() {
        return descriiption;
    }
}

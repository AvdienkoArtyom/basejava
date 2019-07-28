package ru.mail.avdienkoartyom.util;

import ru.mail.avdienkoartyom.model.ContactType;

public class ToHtmlContact {

    public static String toHtml(ContactType contactType, String value){
        return value==null? "Email отсутствует." : contactType.toHtml(value);
    }
}

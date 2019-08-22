package ru.mail.avdienkoartyom.util;

import ru.mail.avdienkoartyom.model.Organization;
import ru.mail.avdienkoartyom.model.Position;

public class HtmlUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String formatDates(Position position) {
        return DateUtil.format(position.getDateStart()) + " - " + DateUtil.format(position.getDateFinish());
    }
}

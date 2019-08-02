package ru.mail.avdienkoartyom;

import ru.mail.avdienkoartyom.model.Resume;

import java.util.UUID;

public class TestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();
    public static final String UUID_5 = UUID.randomUUID().toString();
    public static Resume RESUME_1 = TestDataResume.createResumeUUID(UUID_1, "Петр_1");
    public static Resume RESUME_2 = TestDataResume.createResumeUUIDWithOneContact(UUID_2, "Петр_2");
    public static Resume RESUME_3 = TestDataResume.createResumeUUIDWithoutContact(UUID_3, "Петр_3");
    public static Resume RESUME_4 = TestDataResume.createResumeUUID(UUID_4, "Петр_4");
    public static Resume RESUME_5 = TestDataResume.createResumeUUID(UUID_5, "Петр_5");
}

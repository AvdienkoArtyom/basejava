package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResumeTestData {

    public static Resume createResumeUUID(String uuid, String name) {

        Resume resume = new Resume(uuid, name);

        resume.getContact().put(ContactType.TELEPHONE, "Tel " + uuid);
        resume.getContact().put(ContactType.EMAIL, "Mail " + uuid);
        resume.getContact().put(ContactType.SKYPE, "Skype " + uuid);
        resume.getContact().put(ContactType.PROFILE_LINKEDIN, "LinkedIn " + uuid);
        resume.getContact().put(ContactType.PROFILE_GITHUB, "GitHub " + uuid);
        resume.getContact().put(ContactType.PROFILE_STACKOVERFLOW, "StackOverflow " + uuid);
        resume.getContact().put(ContactType.HOMEPAGE, "Home page " + uuid);

        resume.getSection().put(SectionType.PERSONAL, new SimpleTextSection("Simple text section personal " + uuid));
        resume.getSection().put(SectionType.OBJECTIVE, new SimpleTextSection("Simple text section objective " + uuid));

        ArrayList<String> achievementList = new ArrayList<>();
        achievementList.add("Achievement 1 " + uuid);
        achievementList.add("Achievement 2 " + uuid);
        resume.getSection().put(SectionType.ACHIEVEMENT, new ListSection(achievementList));

        ArrayList<String> qualificationList = new ArrayList<>();
        qualificationList.add("Qualification 1 " + uuid);
        qualificationList.add("Qualification 2 " + uuid);
        resume.getSection().put(SectionType.QUALIFICATIONS, new ListSection(qualificationList));

        List<Organization> experienceList = new ArrayList<>();
        experienceList.add(new Organization("Experience 1 " + uuid, Arrays.asList(new Period(LocalDate.of(2013, Month.OCTOBER, 1), LocalDate.now(), "Status experience 1 " + uuid, "Description experience 1 " + uuid))));
        experienceList.add(new Organization("Experience 2 " + uuid, Arrays.asList(new Period(LocalDate.of(2014, Month.OCTOBER, 1), LocalDate.of(2016, Month.JANUARY, 1), "Status experience 2 " + uuid, "Description experience 2 " + uuid))));
        resume.getSection().put(SectionType.EXPERIENCE, new OrganizationSection(experienceList));

        List<Organization> educationList = new ArrayList<>();
        educationList.add(new Organization("Education 1 " + uuid, Arrays.asList(new Period(LocalDate.of(1997, Month.SEPTEMBER, 1), LocalDate.of(1998, Month.MARCH, 1), "Description education 1 " + uuid))));
        educationList.add(new Organization("Education 2 " + uuid, Arrays.asList(new Period(LocalDate.of(1987, Month.SEPTEMBER, 1), LocalDate.of(1993, Month.JULY, 1), "Description education 2 " + uuid), new Period(LocalDate.of(1993, Month.SEPTEMBER, 1), LocalDate.of(1996, Month.JULY, 1), "Description education 2 " + uuid))));
        resume.getSection().put(SectionType.EDUCATION, new OrganizationSection(educationList));

        return resume;
    }
}


package ru.mail.avdienkoartyom;

import ru.mail.avdienkoartyom.model.*;
import ru.mail.avdienkoartyom.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class TestDataResume {

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
        achievementList.add("Achievement 1 AAAAAAAAAAAAAAA " + uuid);
        achievementList.add("Achievement 2 BAAAAAAAAAAAAAAB" + uuid);
        resume.getSection().put(SectionType.ACHIEVEMENT, new ListSection(achievementList));

        ArrayList<String> qualificationList = new ArrayList<>();
        qualificationList.add("Qualification 1 AQQQQQQQQQQQQQQQQQQQQQQA" + uuid);
        qualificationList.add("Qualification 2 BQQQQQQQQQQQQQQQQQQQQQQB" + uuid);
        resume.getSection().put(SectionType.QUALIFICATIONS, new ListSection(qualificationList));

        List<Organization> experienceList = new ArrayList<>();
        experienceList.add(new Organization("Experience 1 " + uuid,"сайт", Arrays.asList(new Position(DateUtil.of(2013, Month.OCTOBER), LocalDate.now(), "Status experience 1 " + uuid, "Description experience 1 " + uuid))));
        experienceList.add(new Organization("Experience 2 " + uuid,"сайт", Arrays.asList(new Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2016, Month.JANUARY), "Status experience 2 " + uuid, "Description experience 2 " + uuid))));
        resume.getSection().put(SectionType.EXPERIENCE, new OrganizationSection(experienceList));

        List<Organization> educationList = new ArrayList<>();
        educationList.add(new Organization("Education 1 " + uuid,"сайт", Arrays.asList(new Position(DateUtil.of(1997, Month.SEPTEMBER), DateUtil.of(1998, Month.MARCH), "Description education 1 " + uuid))));
        educationList.add(new Organization("Education 2 " + uuid,"сайт", Arrays.asList(new Position(DateUtil.of(1987, Month.SEPTEMBER), DateUtil.of(1993, Month.JULY), "Description education 2 " + uuid), new Position(DateUtil.of(1993, Month.SEPTEMBER), DateUtil.of(1996, Month.JULY), "Description education 2 " + uuid))));
        resume.getSection().put(SectionType.EDUCATION, new OrganizationSection(educationList));

        return resume;
    }

    public static Resume createResumeUUIDWithoutContact(String uuid, String name) {
        Resume resume = new Resume(uuid, name);
        resume.getSection().put(SectionType.PERSONAL, new SimpleTextSection("Simple text section personal " + uuid));
        resume.getSection().put(SectionType.OBJECTIVE, new SimpleTextSection("Simple text section objective " + uuid));

        return resume;
    }

    public static Resume createResumeUUIDWithOneContact(String uuid, String name) {
        Resume resume = new Resume(uuid, name);
        resume.getContact().put(ContactType.HOMEPAGE, "Home page " + uuid);
        resume.getSection().put(SectionType.PERSONAL, new SimpleTextSection("Simple text section personal " + uuid));
        resume.getSection().put(SectionType.OBJECTIVE, new SimpleTextSection("Simple text section objective " + uuid));

        return resume;
    }

    public static void main(String[] args) {

        Resume resume = TestDataResume.createResumeUUID("UUID_1", "Петр_1");
        System.out.println(resume);
    }
}

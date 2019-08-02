package ru.mail.avdienkoartyom.util;

import org.junit.Assert;
import org.junit.Test;
import ru.mail.avdienkoartyom.model.AbstractSection;
import ru.mail.avdienkoartyom.model.Resume;
import ru.mail.avdienkoartyom.model.SectionType;
import ru.mail.avdienkoartyom.model.SimpleTextSection;

import static ru.mail.avdienkoartyom.TestData.RESUME_1;

import static org.junit.Assert.*;

public class JsonParserTest {

    @Test
    public void testResume() {
        String json = JsonParser.write(RESUME_1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        Assert.assertEquals(RESUME_1, resume);
    }

    @Test
    public void write() throws Exception {
        AbstractSection section1 = new SimpleTextSection("Objective1");
        String json = JsonParser.write(section1, AbstractSection.class);
        System.out.println(json);
        AbstractSection section2 = JsonParser.read(json, AbstractSection.class);
        Assert.assertEquals(section1, section2);
    }
}
package ru.mail.avdienkoartyom.storage.SorageStrategy;

import ru.mail.avdienkoartyom.model.*;
import ru.mail.avdienkoartyom.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStreamSerializer implements StorageStrategy{
    private XmlParser xmlParser;

    public XmlStreamSerializer() {
        this.xmlParser = new XmlParser(Resume.class, ContactType.class, ListSection.class, Organization.class, OrganizationSection.class,
                Period.class, SectionType.class, SimpleTextSection.class);
    }

    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try (Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            xmlParser.marshall(resume, writer);
        }
    }

    public Resume doRead(InputStream inputStream) throws IOException {
        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(reader);
        }
    }

}

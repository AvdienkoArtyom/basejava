package ru.mail.avdienkoartyom.storage.diskStrategy;

import ru.mail.avdienkoartyom.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StorageStrategy {
    @Override
    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.writeUTF(resume.getUuid());
            dataOutputStream.writeUTF(resume.getFullName());
            int contactCount = resume.getContact().size();
            dataOutputStream.writeInt(contactCount);
            for (Map.Entry<ContactType, String> entry : resume.getContact().entrySet()) {
                dataOutputStream.writeUTF(entry.getKey().toString());
                dataOutputStream.writeUTF(entry.getValue());
            }
            int sectionCount = resume.getSection().size();
            dataOutputStream.writeInt(sectionCount);
            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSection().entrySet()) {
                SectionType sectionType = entry.getKey();
                AbstractSection abstractSection = entry.getValue();
                dataOutputStream.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dataOutputStream.writeUTF(((SimpleTextSection) abstractSection).getDescription());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeListSection(dataOutputStream, (ListSection) abstractSection);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeOrganization(dataOutputStream, (OrganizationSection) abstractSection);
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        Resume resume;
        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            resume = new Resume(dataInputStream.readUTF(), dataInputStream.readUTF());
            int contactCount = dataInputStream.readInt();
            for (int i = 0; i < contactCount; i++) {
                resume.getContact().put(ContactType.valueOf(dataInputStream.readUTF()), dataInputStream.readUTF());
            }
            int sectionCount = dataInputStream.readInt();
            for (int i = 0; i < sectionCount; i++) {
                SectionType sectionType = SectionType.valueOf(dataInputStream.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.getSection().put(sectionType, new SimpleTextSection(dataInputStream.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.getSection().put(sectionType, new ListSection(readListSection(dataInputStream)));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        resume.getSection().put(sectionType, new OrganizationSection(readOrganization(dataInputStream)));
                        break;
                }
            }
        }
        return resume;
    }

    private void writeListSection(DataOutputStream dataOutputStream, ListSection listSection) throws IOException {
        int listSectionCount = listSection.getList().size();
        dataOutputStream.writeInt(listSectionCount);
        for (String s : listSection.getList()) {
            dataOutputStream.writeUTF(s);
        }
    }

    private void writeOrganization(DataOutputStream dataOutputStream, OrganizationSection organizationSection) throws IOException {
        int organizationSectionCount = organizationSection.getOrganizationList().size();
        dataOutputStream.writeInt(organizationSectionCount);
        for (Organization organization : organizationSection.getOrganizationList()) {
            dataOutputStream.writeUTF(organization.getTitle());
            dataOutputStream.writeUTF(organization.getUrl());
            writePosition(dataOutputStream, organization.getPositionList());
        }
    }

    private void writePosition(DataOutputStream dataOutputStream, List<Position> positionList) throws IOException {
        int periodListCount = positionList.size();
        dataOutputStream.writeInt(periodListCount);
        for (Position position : positionList) {
            writeDatePosition(dataOutputStream, position.getDateStart());
            writeDatePosition(dataOutputStream, position.getDateFinish());
            dataOutputStream.writeUTF(position.getStatus());
            dataOutputStream.writeUTF(position.getDescription());
        }
    }

    private void writeDatePosition(DataOutputStream dataOutputStream, LocalDate localDate) throws IOException {
        dataOutputStream.writeInt(localDate.getYear());
        dataOutputStream.writeInt(localDate.getMonthValue());
        dataOutputStream.writeInt(localDate.getDayOfMonth());
    }

    private List<String> readListSection(DataInputStream dataInputStream) throws IOException {
        List<String> stringList = new ArrayList<>();
        int listSectionCount = dataInputStream.readInt();
        for (int i = 0; i < listSectionCount; i++) {
            stringList.add(dataInputStream.readUTF());
        }
        return stringList;
    }

    private List<Organization> readOrganization(DataInputStream dataInputStream) throws IOException {
        List<Organization> organizationList = new ArrayList<>();
        int organizationSectionCount = dataInputStream.readInt();
        for (int i = 0; i < organizationSectionCount; i++) {
            String title = dataInputStream.readUTF();
            String site = dataInputStream.readUTF();
            organizationList.add(new Organization(title, readPosition(dataInputStream), site));
        }
        return organizationList;
    }

    private List<Position> readPosition(DataInputStream dataInputStream) throws IOException {
        List<Position> positionList = new ArrayList<>();
        int periodCount = dataInputStream.readInt();
        for (int i = 0; i < periodCount; i++) {
            LocalDate startDate = LocalDate.of(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt());
            LocalDate endDate = LocalDate.of(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt());
            String status = dataInputStream.readUTF();
            String description = dataInputStream.readUTF();
            Position position;
            if (status.isEmpty()) {
                position = new Position(startDate, endDate, status, description);
            } else
                position = new Position(startDate, endDate, status, description);
            positionList.add(position);
        }
        return positionList;
    }
}

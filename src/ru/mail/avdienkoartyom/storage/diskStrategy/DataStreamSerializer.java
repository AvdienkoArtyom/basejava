package ru.mail.avdienkoartyom.storage.diskStrategy;

import ru.mail.avdienkoartyom.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamSerializer implements StorageStrategy {
    @Override
    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.writeUTF(resume.getUuid());
            dataOutputStream.writeUTF(resume.getFullName());

            writeCollection(dataOutputStream, resume.getContact().entrySet(), entry -> {
                dataOutputStream.writeUTF(entry.getKey().name());
                dataOutputStream.writeUTF(entry.getValue());
            });

            writeCollection(dataOutputStream, resume.getSection().entrySet(), entry -> {
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
                        writeCollection(dataOutputStream, ((ListSection) abstractSection).getList(), dataOutputStream::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dataOutputStream, ((OrganizationSection) abstractSection).getOrganizationList(), org -> {
                            dataOutputStream.writeUTF(org.getTitle());
                            dataOutputStream.writeUTF(org.getUrl());
                            writeCollection(dataOutputStream, org.getPositionList(), position -> {
                                writeDatePosition(dataOutputStream, position.getDateStart());
                                writeDatePosition(dataOutputStream, position.getDateFinish());
                                dataOutputStream.writeUTF(position.getStatus());
                                dataOutputStream.writeUTF(position.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        Resume resume;
        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            resume = new Resume(dataInputStream.readUTF(), dataInputStream.readUTF());

            readCollection(dataInputStream, () -> resume.getContact().put(ContactType.valueOf(dataInputStream.readUTF()), dataInputStream.readUTF()));

            readCollection(dataInputStream, () -> {
                SectionType sectionType = SectionType.valueOf(dataInputStream.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.getSection().put(sectionType, new SimpleTextSection(dataInputStream.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.getSection().put(sectionType, new ListSection(readList(dataInputStream, dataInputStream::readUTF)));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        resume.getSection().put(sectionType, new OrganizationSection(readList(dataInputStream, () ->
                                new Organization(dataInputStream.readUTF(), dataInputStream.readUTF(), readList(dataInputStream, () ->
                                        new Position(readDatePosition(dataInputStream), readDatePosition(dataInputStream), dataInputStream.readUTF(), dataInputStream.readUTF()))))));
                        break;
                }
            });
        }
        return resume;
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private interface ElementProcessor {
        void process() throws IOException;
    }

    private interface MyWriter<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, MyWriter<T> myWriter) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            myWriter.write(item);
        }
    }

    private void readCollection(DataInputStream dis, ElementProcessor processor) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            processor.process();
        }
    }

    private void writeDatePosition(DataOutputStream dataOutputStream, LocalDate localDate) throws IOException {
        dataOutputStream.writeInt(localDate.getYear());
        dataOutputStream.writeInt(localDate.getMonthValue());
        dataOutputStream.writeInt(localDate.getDayOfMonth());
    }

    private LocalDate readDatePosition(DataInputStream dataInputStream) throws IOException {
        return LocalDate.of(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt());
    }
}

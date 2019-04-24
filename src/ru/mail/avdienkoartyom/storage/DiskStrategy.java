package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.model.Resume;

import java.io.InputStream;
import java.io.OutputStream;

public interface DiskStrategy {
    void doWrite(Resume resume, OutputStream outputStream) ;
    Resume doRead(InputStream inputStream);
}

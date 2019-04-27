package ru.mail.avdienkoartyom.storage.SorageStrategy;

import ru.mail.avdienkoartyom.model.Resume;

import java.io.InputStream;
import java.io.OutputStream;

public interface StorageStrategy {
    void doWrite(Resume resume, OutputStream outputStream) ;
    Resume doRead(InputStream inputStream);
}

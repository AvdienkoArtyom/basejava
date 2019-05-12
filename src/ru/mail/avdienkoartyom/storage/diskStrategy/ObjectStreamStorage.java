package ru.mail.avdienkoartyom.storage.diskStrategy;

import ru.mail.avdienkoartyom.exception.StorageException;
import ru.mail.avdienkoartyom.model.Resume;

import java.io.*;

public class ObjectStreamStorage implements StorageStrategy {

    @Override
    public void doWrite(Resume resume, OutputStream outputStream) {
        try (ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(resume);
        } catch (IOException e) {
            throw new StorageException("Error write resume.", null, e);
        }
    }

    @Override
    public Resume doRead(InputStream inputStream) {
        try (ObjectInputStream ois = new ObjectInputStream(inputStream)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume.", null, e);
        } catch (IOException e) {
            throw new StorageException("Error read resume.", null, e);
        }
    }
}

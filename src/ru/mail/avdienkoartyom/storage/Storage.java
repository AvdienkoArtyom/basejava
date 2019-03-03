package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.model.Resume;

import java.util.List;

public interface Storage {

    void save(Resume resume);

    void update(Resume resume);

    Resume get(String uuid);

    List<Resume> getAllSorted();

    void delete(String uuid);

    void clear();

    int size();
}

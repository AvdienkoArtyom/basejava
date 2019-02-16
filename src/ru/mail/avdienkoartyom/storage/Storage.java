package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.model.Resume;

public interface Storage {

    void save(Resume resume);

    void update(Resume resume);

    Resume get(String uuid);

    Resume[] getAll();

    void delete(String uuid);

    void clear();

    int size();
}

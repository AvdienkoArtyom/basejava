package ru.mail.avdienkoartyom.Storage;

import ru.mail.avdienkoartyom.model.Resume;

public interface Storage {
    void clear();

    void save(Resume var1);

    Resume get(String var1);

    void delete(String var1);

    void update(Resume var1);

    Resume[] getAll();

    int size();
}

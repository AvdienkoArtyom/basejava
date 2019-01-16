package ru.mail.avdienkoartyom.Storage;

import ru.mail.avdienkoartyom.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1 && uuid != null) {
            System.out.println("Запись " + uuid + " в базе данных ru.mail.avdienkoartyom.model.Resume найдена.");
            return storage[index];
        } else {
            System.out.println("Запись " + uuid + " в базе данных ru.mail.avdienkoartyom.model.Resume отсутствует.");
            return null;
        }
    }

    public void update(Resume resume) {
        if (storage != null) {
            int position = getIndex(resume.getUuid());
            if (position > -1) {
                storage[position] = resume;
                System.out.println("Запись " + storage[position] + " в базе данных ru.mail.avdienkoartyom.model.Resume обновлена.");
            } else {
                System.out.println("Запись " + resume + " в базе данных ru.mail.avdienkoartyom.model.Resume отсутствует.");
            }
        }
    }

    abstract int getIndex(String uuid);

    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }
}

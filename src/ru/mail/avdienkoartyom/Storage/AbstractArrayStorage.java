package ru.mail.avdienkoartyom.Storage;

import ru.mail.avdienkoartyom.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract int getIndex(String uuid);

    protected abstract void deleteElement(int index);

    protected abstract void saveElement(Resume resume, int index);

    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            System.out.println("Нет места для записи в БД!");
        } else if (getIndex(resume.getUuid()) < 0) {
            if (size == 0) {
                storage[size] = resume;
                size++;
            } else {
                saveElement(resume, getIndex(resume.getUuid()));
                size++;
            }
        } else {
            System.out.println("Запись " + resume.getUuid() + " в базе данных ru.mail.avdienkoartyom.model.Resume уже присутствовала.");
        }

    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            deleteElement(index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Запись " + uuid + " в базе данных ru.mail.avdienkoartyom.model.Resume отсутствует.");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            System.out.println("Запись " + uuid + " в базе данных ru.mail.avdienkoartyom.model.Resume отсутствует.");
            return null;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        } else {
            System.out.println("Запись " + resume + " в базе данных ru.mail.avdienkoartyom.model.Resume отсутствует.");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }
}

package ru.mail.avdienkoartyom.Storage;

import ru.mail.avdienkoartyom.model.Resume;

import java.util.Arrays;


public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public Resume get(String uuid) {
        if (!uuidCorrect(uuid)) {
            return null;
        } else {
            int index = getIndex(uuid);
            if (index > -1) {
                System.out.println("Запись " + uuid + " в базе данных ru.mail.avdienkoartyom.model.Resume найдена.");
                return storage[index];
            } else {
                System.out.println("Запись " + uuid + " в базе данных ru.mail.avdienkoartyom.model.Resume отсутствует.");
                return null;
            }
        }
    }

    public void update(Resume resume) {
        if (resumeCorrect(resume)) {
            int index = getIndex(resume.getUuid());
            if (index > -1) {
                storage[index] = resume;
                System.out.println("Запись " + storage[index] + " в базе данных ru.mail.avdienkoartyom.model.Resume обновлена.");
            } else {
                System.out.println("Запись " + resume + " в базе данных ru.mail.avdienkoartyom.model.Resume отсутствует.");
            }

        }
    }

    public abstract int getIndex(String uuid);

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size );
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public boolean uuidCorrect(String uuid) {
        if (uuid == null && uuid.equals("")) {
            System.out.println("Ошибка, некорректное значение uuid!");
            return false;
        } else {
            return true;
        }

    }

    public boolean resumeCorrect(Resume resume) {
        if (resume == null && uuidCorrect(resume.getUuid())) {
            System.out.println("Ошибка, некорректное значение Resume!");
            return false;
        } else {
            return true;

        }
    }
}

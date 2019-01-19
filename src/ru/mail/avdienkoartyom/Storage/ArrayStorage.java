package ru.mail.avdienkoartyom.Storage;

import ru.mail.avdienkoartyom.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        if (resumeCorrect(resume)) {
            if (size == STORAGE_LIMIT) {
                System.out.println("Нет места для записи в БД!");
            } else {
                if (getIndex(resume.getUuid()) < 0) {
                    storage[size] = resume;
                    size++;
                    System.out.println("В базу данных ru.mail.avdienkoartyom.model.Resume добавлена новая запись " + resume.getUuid() + ".");
                } else {
                    System.out.println("Запись " + resume.getUuid() + " в базе данных ru.mail.avdienkoartyom.model.Resume уже присутствовала.");
                }
            }
        }
    }

    public void delete(String uuid) {
        if (uuidCorrect(uuid)) {
            int index = getIndex(uuid);
            if (index > -1) {
                storage[index] = storage[size-1];
                storage[size - 1] = null;
                size--;
                System.out.println("Запись " + uuid + " в базе данных ru.mail.avdienkoartyom.model.Resume удалена.");
            } else {
                System.out.println("Запись " + uuid + " в базе данных ru.mail.avdienkoartyom.model.Resume отсутствует.");
            }
        }
    }

    @Override
    public int getIndex(String uuid) {
        if (uuidCorrect(uuid)) {
            for (int i = 0; i < size; ++i) {
                if (storage[i].getUuid().equals(uuid)) {
                    return i;
                }
            }
        }
        return -1;
    }

}

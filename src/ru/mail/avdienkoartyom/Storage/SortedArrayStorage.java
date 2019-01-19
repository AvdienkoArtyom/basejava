package ru.mail.avdienkoartyom.Storage;

import ru.mail.avdienkoartyom.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        if (resumeCorrect(resume)) {
            if (size == STORAGE_LIMIT) {
                System.out.println("Нет места для записи в БД!");
            } else {
                if (getIndex(resume.getUuid()) < 0) {
                    if (size == 0) {
                        storage[size] = resume;
                        size++;
                    } else {
                        int indexPaste = Arrays.binarySearch(storage, 0, size , resume);
                        System.arraycopy(storage, -(indexPaste) - 1, storage, -(indexPaste), indexPaste + 1 + size);
                        storage[-(indexPaste) -1] = resume;
                        size++;
                    }
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
                System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
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
            Resume searchKey = new Resume();
            searchKey.setUuid(uuid);
            return Arrays.binarySearch(storage, 0, size, searchKey);
        } else {
            return -1;
        }
    }

}

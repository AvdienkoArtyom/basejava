package ru.mail.avdienkoartyom.Storage;

import ru.mail.avdienkoartyom.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        if (size == STORAGE_LIMIT) {
            System.out.println("Нет места для записи в БД!");
        } else {
            if (getIndex(r.getUuid()) < 0) {
                storage[size] = r;
                ++size;
                System.out.println("В базу данных ru.mail.avdienkoartyom.model.Resume добавлена новая запись " + r.getUuid() + ".");
            } else {
                System.out.println("Запись " + r.getUuid() + " в базе данных ru.mail.avdienkoartyom.model.Resume уже присутствовала.");
            }
        }
        for (int i = size - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (storage[j].compareTo(storage[j + 1]) > 0) {
                    Resume resume = storage[j];
                    storage[j] = storage[j + 1];
                    storage[j + 1] = resume;
                }
            }
        }

    }

    public void delete(String uuid) {
        int i = getIndex(uuid);
        System.arraycopy(storage, i + 1, storage, i, size - i - 1);
        storage[size - 1] = null;
        --size;
    }

    public int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size(), searchKey);
    }
}

package ru.mail.avdienkoartyom.Storage;

import ru.mail.avdienkoartyom.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;

    public void save(Resume r) {
        if (r != null && getIndex(r.getUuid()) == -1 && size < storage.length) {
            storage[size] = r;
            size++;
            System.out.println("В базу данных ru.mail.avdienkoartyom.model.Resume добавлена новая запись " + r.getUuid() + ".");
        } else {
            System.out.println("Запись " + r.getUuid() + " в базе данных ru.mail.avdienkoartyom.model.Resume уже присутствовала.");
        }
    }

    public void delete(String uuid) {
        int position = getIndex(uuid);
        if (position > -1) {
            System.arraycopy(storage, position + 1, storage, position, size - 1 - position);
            storage[size - 1] = null;
            size--;
            System.out.println("Запись " + uuid + " в базе данных ru.mail.avdienkoartyom.model.Resume удалена.");
        } else {
            System.out.println("Запись " + uuid + " в базе данных ru.mail.avdienkoartyom.model.Resume отсутствует.");
        }
    }

    public int getIndex(String uuid) {
        int index = -1;
        for (int i = 0; i < size; ++i) {
            if (storage[i].getUuid().equals(uuid)) {
                index = i;
                break;
            }
        }
        return index;
    }

}

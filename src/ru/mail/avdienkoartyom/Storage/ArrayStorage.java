package ru.mail.avdienkoartyom.Storage;

import ru.mail.avdienkoartyom.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public int getIndex(String uuid) {
        for (int i = 0; i < size; ++i) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void saveElement(Resume resume) {
        storage[size] = resume;
        size++;
    }

    @Override
    public void deleteElement(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }


}

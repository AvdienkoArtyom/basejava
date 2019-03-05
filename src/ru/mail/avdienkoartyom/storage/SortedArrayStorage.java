package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "anyName");
        return Arrays.binarySearch(storage, 0, size, searchKey,  Comparator.comparing(Resume::getUuid));
    }

    @Override
    protected void saveElement(Resume resume, int index) {
        int indexPos = -index - 1;
        System.arraycopy(storage, indexPos, storage, indexPos + 1, size - indexPos);
        storage[indexPos] = resume;
    }

    @Override
    protected void deleteElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }
}
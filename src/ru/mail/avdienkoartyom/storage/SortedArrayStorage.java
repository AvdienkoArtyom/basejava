package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey, new SortedArrayStorageComparator());
    }

    @Override
    protected void saveElement(Resume resume, int index) {
        index = -(index) - 1;
        System.arraycopy(storage, index, storage, index + 1, -index + size);
        storage[index] = resume;
    }

    @Override
    protected void deleteElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }

    private class SortedArrayStorageComparator implements Comparator<Resume> {

        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }
}
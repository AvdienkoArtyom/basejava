package ru.mail.avdienkoartyom.Storage;

import ru.mail.avdienkoartyom.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    public void saveElement(Resume resume) {
        int indexPaste = getIndex(resume.getUuid());
        indexPaste = -(indexPaste) - 1;
        System.arraycopy(storage, indexPaste, storage, indexPaste + 1, -indexPaste + 1 + size);
        storage[indexPaste] = resume;
        size++;
    }

    @Override
    public void deleteElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        storage[size - 1] = null;
        size--;
    }
}

package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.exception.StorageException;
import ru.mail.avdienkoartyom.model.Resume;

import java.util.*;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void deleteElement(int index);

    protected abstract void saveElement(Resume resume, int index);

    @Override
    protected void doSave(Resume resume, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage Overflow", resume.getUuid());
        } else if ((Integer) index < 0) {
            if (size == 0) {
                storage[size] = resume;
            } else {
                saveElement(resume, (Integer) index);
            }
            size++;
        }
    }

    public void doDelete(Object index) {
        deleteElement((Integer) index);
        storage[size - 1] = null;
        size--;

    }

    public Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage[(Integer) searchKey] = resume;
    }

    public List<Resume> doGetAllSorted() {
        List<Resume> arrayList = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(storage, 0, size)));
        return arrayList;
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index > -1;
    }
}

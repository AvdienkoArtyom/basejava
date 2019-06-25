package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.exception.ExistStorageException;
import ru.mail.avdienkoartyom.exception.NoExistStorageException;
import ru.mail.avdienkoartyom.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<T> implements Storage {

    protected abstract void doSave(Resume resume, T searchKey);

    protected abstract Resume doGet(T searchKey);

    protected abstract List<Resume> doGetAll();

    protected abstract void doUpdate(Resume resume, T searchKey);

    protected abstract void doDelete(T searchKey);

    protected abstract T getSearchKey(String uuid);

    protected abstract boolean isExist(T uuid);

    public static Comparator resumeComparator(){
        return Comparator.comparing(Resume::getFullName)
                .thenComparing(Resume::getUuid);
    }

    public void save(Resume resume) {
        T searchKey = getNotExistedSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    public Resume get(String uuid) {
        T searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    public List<Resume> getAllSorted(){
        List<Resume> list = doGetAll();
        list.sort(resumeComparator());
        return list;
    }

    public void update(Resume resume) {
        T searchKey = getExistedSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    public void delete(String uuid) {
        T searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    private T getExistedSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NoExistStorageException(uuid);
        }
        return searchKey;
    }

    private T getNotExistedSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

}

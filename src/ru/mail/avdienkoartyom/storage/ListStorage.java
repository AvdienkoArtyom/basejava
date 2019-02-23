package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> resumes = new ArrayList<>();

    @Override
    public void clear() {
        resumes.clear();
    }

    @Override
    public int size() {
        return resumes.size();
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        resumes.add(resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return resumes.get((Integer) searchKey);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        resumes.set((Integer) searchKey, resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        int index = (Integer) searchKey;
        resumes.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return resumes.toArray(new Resume[]{});
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < resumes.size(); i++) {
            if (uuid.equals(resumes.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }
}

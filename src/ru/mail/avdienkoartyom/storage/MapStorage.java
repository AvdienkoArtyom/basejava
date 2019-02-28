package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.model.Resume;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected final Map<String, Resume> resumes = new LinkedHashMap<>();

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
        resumes.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return resumes.get(searchKey);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        resumes.replace(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        resumes.remove(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        if (resumes.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }

    @Override
    protected boolean isExist(Object index) {
        return getSearchKey((String)index)!=null;
    }

    @Override
    public Resume[] getAll() {
            return  resumes.values().toArray(new Resume[]{});
    }

}

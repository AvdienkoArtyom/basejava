package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {
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
        return uuid;
    }

    @Override
    protected boolean isExist(Object index) {
        return resumes.containsKey(getSearchKey((String) index));
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>();
        Collections.addAll(list, resumes.values().toArray(new Resume[]{}));
        Collections.sort(list);
        return list;
    }
}

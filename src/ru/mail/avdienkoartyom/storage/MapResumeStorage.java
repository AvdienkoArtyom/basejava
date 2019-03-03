package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {
    protected final Map<Resume, Object> resumes = new LinkedHashMap<>();

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
        resumes.put(resume, searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        Resume resume;
        if (resumes.containsValue(searchKey)) {
            for (Map.Entry<Resume, Object> entry : resumes.entrySet()) {
                if (entry.getValue().equals(searchKey)) {
                    resume = entry.getKey();
                    return resume;
                }
            }

        }
        return null;
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        resumes.replace(resume, searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        resumes.remove(new Resume((String) searchKey), searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object index) {
        return resumes.containsValue(getSearchKey((String) index));
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>();
        Collections.addAll(list, resumes.keySet().toArray(new Resume[]{}));
        Collections.sort(list);
        return list;
    }
}

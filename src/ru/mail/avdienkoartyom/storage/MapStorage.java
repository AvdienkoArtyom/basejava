package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.model.Resume;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected final Map<String, Resume> resumes = new HashMap<>();

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
        String uuidKey = null;
        for (Map.Entry<String, Resume> entry : resumes.entrySet()) {
            if (entry.getValue().getUuid().equals(uuid)) {
                uuidKey = entry.getKey();
                System.out.println(uuid);
            }
        }
        return uuidKey;
    }

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }

    @Override
    public Resume[] getAll() {
        int index = resumes.size();
        Resume[] resume = new Resume[index];
        for (Map.Entry<String, Resume> entry : resumes.entrySet()) {
            index--;
            resume[index] = entry.getValue();
        }
        return resume;
    }

}

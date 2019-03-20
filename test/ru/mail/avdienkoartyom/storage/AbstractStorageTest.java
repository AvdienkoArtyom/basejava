package ru.mail.avdienkoartyom.storage;

import org.junit.Before;
import org.junit.Test;
import ru.mail.avdienkoartyom.exception.ExistStorageException;
import ru.mail.avdienkoartyom.exception.NoExistStorageException;
import ru.mail.avdienkoartyom.model.Resume;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public abstract class AbstractStorageTest {
    protected Storage storage;
    private static final String UUID_1 = "UUID_1";
    private static final String UUID_2 = "UUID_2";
    private static final String UUID_3 = "UUID_3";
    private static final String UUID_4 = "UUID_4";
    private static final String UUID_5 = "UUID_5";
    private static final String FULL_NAME_1 = "Петр_1";
    private static final String FULL_NAME_2 = "Петр_2";
    private static final String FULL_NAME_3 = "Петр_3";
    private static final String FULL_NAME_4 = "Петр_4";
    private static final String FULL_NAME_5 = "Петр_5";
    private Resume RESUME_1 = new Resume(UUID_1, FULL_NAME_1);
    private Resume RESUME_2 = new Resume(UUID_2, FULL_NAME_2);
    private Resume RESUME_3 = new Resume(UUID_3, FULL_NAME_3);
    private Resume RESUME_4 = new Resume(UUID_4, FULL_NAME_4);
    private Resume RESUME_5 = new Resume(UUID_5, FULL_NAME_5);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertEquals(RESUME_4, storage.get(UUID_4));
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NoExistStorageException.class)
    public void getNoExist() {
        storage.get("Dammy");
    }

    @Test
    public void getAllSorted() {
        List<Resume> resumeList = storage.getAllSorted();
        assertEquals(3, resumeList.size());
        assertEquals(Arrays.asList(RESUME_1, RESUME_2, RESUME_3), resumeList);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "newName");
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NoExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_5);
    }

    @Test(expected = NoExistStorageException.class)
    public void delete() {
        storage.delete(UUID_3);
        assertEquals(2, storage.size());
        storage.delete(UUID_3);
    }

    @Test(expected = NoExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_5);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

}
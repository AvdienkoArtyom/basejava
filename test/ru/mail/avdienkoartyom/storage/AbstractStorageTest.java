package ru.mail.avdienkoartyom.storage;

import org.junit.Before;
import org.junit.Test;
import ru.mail.avdienkoartyom.Config;
import ru.mail.avdienkoartyom.TestDataResume;
import ru.mail.avdienkoartyom.exception.ExistStorageException;
import ru.mail.avdienkoartyom.exception.NoExistStorageException;
import ru.mail.avdienkoartyom.model.*;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;


public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.getInstance().getStorageDir();
    protected Storage storage;
    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();
    private static final String UUID_5 = UUID.randomUUID().toString();
    private Resume RESUME_1 = TestDataResume.createResumeUUID(UUID_1, "Петр_1");
    private Resume RESUME_2 = TestDataResume.createResumeUUID(UUID_2, "Петр_2");
    private Resume RESUME_3 = TestDataResume.createResumeUUID(UUID_3, "Петр_3");
    private Resume RESUME_4 = TestDataResume.createResumeUUID(UUID_4, "Петр_4");
    private Resume RESUME_5 = TestDataResume.createResumeUUID(UUID_5, "Петр_5");

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
        storage.get("Dummy");
    }

    @Test
    public void getAllSorted() {
        List<Resume> resumeListTest = storage.getAllSorted();
        assertEquals(3, resumeListTest.size());
        List<Resume> resumeList = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        Collections.sort(resumeList, Resume.resumeComparator());
        assertEquals(resumeList, resumeListTest);
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
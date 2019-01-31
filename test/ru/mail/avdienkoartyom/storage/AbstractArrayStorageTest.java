package ru.mail.avdienkoartyom.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.mail.avdienkoartyom.exception.ExistStorageException;
import ru.mail.avdienkoartyom.exception.NoExistStorageException;
import ru.mail.avdienkoartyom.exception.StorageException;
import ru.mail.avdienkoartyom.model.Resume;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    public static final String UUID_1 = "UUID_1";
    public static final String UUID_2 = "UUID_2";
    public static final String UUID_3 = "UUID_3";
    public static final String UUID_4 = "UUID_4";
    public static final String UUID_5 = "UUID_5";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void save() {
        int i = storage.size();
        storage.save(new Resume(UUID_4));
        Assert.assertEquals(i + 1, storage.size());
    }

    @Test(expected = NoExistStorageException.class)
    public void delete() {
        try {
            int i = storage.size();
            storage.save(new Resume(UUID_4));
            storage.delete(UUID_4);
            Assert.assertEquals(i, storage.size());
        } catch (Exception e) {
            fail();
        }
        storage.get(UUID_4);
    }

    @Test
    public void get() {
        storage.save(new Resume(UUID_5));
        Assert.assertEquals(UUID_5, storage.get(UUID_5).getUuid());
    }

    @Test(expected = NoExistStorageException.class)
    public void getNoExist() throws Exception {
        storage.get("Dammy");
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test
    public void update() {
    }

    @Test
    public void getAll() throws Exception {
        int size = storage.size();
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(size, resumes.length);
        Resume resume;
        for (int i = 0; i < storage.size(); i++) {
            resume = resumes[i];
            Assert.assertEquals(resume, storage.get(resume.getUuid()));
        }
    }

    @Test
    public void size() throws Exception {
        setUp();
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = StorageException.class)
    public void testArrayOverflow() {
        try {
            storage.clear();
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            fail();
        }
        storage.save(new Resume());
    }
}
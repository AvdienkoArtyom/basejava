package ru.mail.avdienkoartyom.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.mail.avdienkoartyom.exception.ExistStorageException;
import ru.mail.avdienkoartyom.exception.NoExistStorageException;
import ru.mail.avdienkoartyom.exception.StorageException;
import ru.mail.avdienkoartyom.model.Resume;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private Resume resume_1 = new Resume(UUID_1);
    private Resume resume_2 = new Resume(UUID_2);
    private Resume resume_3 = new Resume(UUID_3);
    private Resume resume_4 = new Resume(UUID_4);
    private Resume resume_5 = new Resume(UUID_5);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "UUID_1";
    private static final String UUID_2 = "UUID_2";
    private static final String UUID_3 = "UUID_3";
    private static final String UUID_4 = "UUID_4";
    private static final String UUID_5 = "UUID_5";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume_1);
        storage.save(resume_2);
        storage.save(resume_3);
    }

    @Test
    public void save() {
        storage.save(resume_4);
        Assert.assertEquals(resume_4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume_1);
    }

    @Test(expected = StorageException.class)
    public void testArrayOverflow() {
        try {
            storage.clear();
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (ExistStorageException e) {
            fail("Error filling array");
        }
        storage.save(new Resume());
    }

    @Test
    public void get() {
        Assert.assertEquals(resume_1, storage.get(UUID_1));
    }

    @Test
    public void getAll() {
        Resume[] resumes = new Resume[3];
        resumes[0] = resume_1;
        resumes[1] = resume_2;
        resumes[2] = resume_3;
        Assert.assertArrayEquals(resumes, storage.getAll());
    }

    @Test(expected = NoExistStorageException.class)
    public void getNoExist() {
        storage.get("Dammy");
    }

    @Test
    public void update() {
        storage.update(resume_1);
        Assert.assertEquals(resume_1, storage.get(UUID_1));
    }

    @Test(expected = NoExistStorageException.class)
    public void updateNotExist() {
        storage.update(resume_5);
    }

    @Test
    public void delete() {
        storage.delete(UUID_3);
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NoExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_5);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

}
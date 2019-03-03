package ru.mail.avdienkoartyom.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.mail.avdienkoartyom.exception.ExistStorageException;
import ru.mail.avdienkoartyom.exception.NoExistStorageException;
import ru.mail.avdienkoartyom.exception.StorageException;
import ru.mail.avdienkoartyom.model.Resume;

import java.util.List;

import static org.junit.Assert.*;


public abstract class AbstractStorageTest {
    private Storage storage;
    private Resume RESUME_1 = new Resume(UUID_1);
    private Resume RESUME_2 = new Resume(UUID_2);
    private Resume RESUME_3 = new Resume(UUID_3);
    private Resume RESUME_4 = new Resume(UUID_4);
    private Resume RESUME_5 = new Resume(UUID_5);
    private static final String UUID_1 = "UUID_1";
    private static final String UUID_2 = "UUID_2";
    private static final String UUID_3 = "UUID_3";
    private static final String UUID_4 = "UUID_4";
    private static final String UUID_5 = "UUID_5";

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        Assert.assertEquals(RESUME_4, storage.get(UUID_4));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = StorageException.class)
    public void testArrayOverflow() {
        try {
            storage.clear();
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail("Error filling array");
        }
        storage.save(new Resume());
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NoExistStorageException.class)
    public void getNoExist() {
        storage.get("Dammy");
    }

    @Test
    public void getAllSorted() {
        Resume[] resumes = new Resume[3];
        List<Resume> list = storage.getAllSorted();
        Resume[] resumesStorage = list.toArray(new Resume[]{});
        resumes[0] = RESUME_1;
        resumes[1] = RESUME_2;
        resumes[2] = RESUME_3;
        Assert.assertArrayEquals(resumes, resumesStorage);
        Assert.assertEquals(3, resumesStorage.length);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        Assert.assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NoExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_5);
    }

    @Test(expected = NoExistStorageException.class)
    public void delete() {
        storage.delete(UUID_3);
        Assert.assertEquals(2, storage.size());
        storage.delete(UUID_3);
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
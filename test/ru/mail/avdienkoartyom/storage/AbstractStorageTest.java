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
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static ru.mail.avdienkoartyom.storage.AbstractStorage.resumeComparator;


public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.getInstance().getStorageDir();
    protected Storage storage;
    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();
    private static final String UUID_5 = UUID.randomUUID().toString();
    private Resume RESUME_1 = TestDataResume.createResumeUUID(UUID_1, "Петр_1");
    private Resume RESUME_2 = TestDataResume.createResumeUUIDWithoutContact(UUID_2, "Петр-2");
    private Resume RESUME_3 = TestDataResume.createResumeUUIDWithOneContact(UUID_3, "Петр_3");
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
        assertEquals(storage.get(UUID_4), RESUME_4);
        assertEquals(storage.size(), 4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void get() {
        assertEquals(storage.get(UUID_1), RESUME_1);
        assertEquals(storage.get(UUID_2), RESUME_2);

    }

    @Test(expected = NoExistStorageException.class)
    public void getNoExist() {
        storage.get("Dummy");
    }

    @Test
    public void getAllSorted() {
        List<Resume> resumeListTest = storage.getAllSorted();
        assertEquals(resumeListTest.size(), 3);
        List<Resume> resumeList = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        resumeList.sort(resumeComparator());
        assertEquals(resumeListTest, resumeList);
    }

    @Test
    public void update() {
        RESUME_1.setFullName("newName");
        RESUME_1.getContact().put(ContactType.TELEPHONE, "Tel New" + RESUME_1.getUuid());
        RESUME_1.getContact().put(ContactType.EMAIL, "Mail New" + RESUME_1.getUuid());
        RESUME_1.getContact().put(ContactType.SKYPE, "Skype New" + RESUME_1.getUuid());
        RESUME_1.getContact().put(ContactType.PROFILE_LINKEDIN, "LinkedIn New" + RESUME_1.getUuid());
        RESUME_1.getContact().put(ContactType.PROFILE_GITHUB, "GitHub New" + RESUME_1.getUuid());
        RESUME_1.getContact().put(ContactType.PROFILE_STACKOVERFLOW, "StackOverflow New" + RESUME_1.getUuid());
        RESUME_1.getContact().put(ContactType.HOMEPAGE, "Home page New" + RESUME_1.getUuid());
        storage.update(RESUME_1);
        assertEquals(storage.get(UUID_1), RESUME_1);
        RESUME_3.getContact().put(ContactType.SKYPE, "Skype DDDDDDNew" + RESUME_3.getUuid());
        RESUME_3.getContact().put(ContactType.PROFILE_LINKEDIN, "LinkedIn New" + RESUME_3.getUuid());
        RESUME_3.getContact().put(ContactType.PROFILE_GITHUB, "GitHub New" + RESUME_3.getUuid());
        storage.update(RESUME_3);
        assertEquals(storage.get(UUID_3), RESUME_3);


    }

    @Test(expected = NoExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_5);
    }

    @Test(expected = NoExistStorageException.class)
    public void delete() {
        storage.delete(UUID_3);
        assertEquals(storage.size(), 2);
        storage.delete(UUID_3);
    }

    @Test(expected = NoExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_5);
    }

    @Test
    public void size() {
        assertEquals(storage.size(), 3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(storage.size(), 0);
    }
}
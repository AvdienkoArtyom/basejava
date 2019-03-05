package ru.mail.avdienkoartyom.storage;

import org.junit.Test;
import ru.mail.avdienkoartyom.exception.StorageException;
import ru.mail.avdienkoartyom.model.Resume;

import static org.junit.Assert.fail;

public class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void testArrayOverflow() {
        try {
            storage.clear();
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("i"));
            }
        } catch (StorageException e) {
            fail("Error filling array");
        }
        storage.save(new Resume("Overflow"));
    }
}

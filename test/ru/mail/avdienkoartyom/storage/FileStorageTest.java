package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.storage.SorageStrategy.ObjectStreamStorage;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStorage()));
    }
}

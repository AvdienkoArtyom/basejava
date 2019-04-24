package ru.mail.avdienkoartyom.storage;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStorage()));
    }
}

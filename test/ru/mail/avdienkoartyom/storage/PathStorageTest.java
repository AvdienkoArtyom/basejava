package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.storage.SorageStrategy.ObjectStreamStorage;

import java.nio.file.Paths;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(Paths.get(STORAGE_DIR.getAbsolutePath()), new ObjectStreamStorage()));
    }
}

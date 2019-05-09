package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.storage.SorageStrategy.DataStreamSerializer;

import java.nio.file.Paths;

public class DataPathStorageTest extends AbstractStorageTest {
    public DataPathStorageTest() {
        super(new PathStorage(Paths.get(STORAGE_DIR.getAbsolutePath()), new DataStreamSerializer()));
    }
}

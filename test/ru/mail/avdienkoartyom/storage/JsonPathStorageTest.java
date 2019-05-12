package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.storage.diskStrategy.JsonStreamSerializer;

import java.nio.file.Paths;

public class JsonPathStorageTest extends AbstractStorageTest {
    public JsonPathStorageTest() {
        super(new PathStorage(Paths.get(STORAGE_DIR.getAbsolutePath()), new JsonStreamSerializer()));
    }
}

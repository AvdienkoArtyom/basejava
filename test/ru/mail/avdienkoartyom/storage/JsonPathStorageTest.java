package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.storage.SorageStrategy.JsonStreamSerializer;
import ru.mail.avdienkoartyom.storage.SorageStrategy.XmlStreamSerializer;

import java.nio.file.Paths;

public class JsonPathStorageTest extends AbstractStorageTest {
    public JsonPathStorageTest() {
        super(new PathStorage(Paths.get(STORAGE_DIR.getAbsolutePath()), new JsonStreamSerializer()));
    }
}

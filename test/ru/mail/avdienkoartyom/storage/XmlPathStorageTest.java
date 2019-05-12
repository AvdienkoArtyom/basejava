package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.storage.diskStrategy.XmlStreamSerializer;

import java.nio.file.Paths;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(Paths.get(STORAGE_DIR.getAbsolutePath()), new XmlStreamSerializer()));
    }
}

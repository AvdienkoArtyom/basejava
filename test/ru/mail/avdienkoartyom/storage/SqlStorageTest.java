package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.Config;

public class SqlStorageTest extends AbstractStorageTest{
    public SqlStorageTest() {
        super(Config.getInstance().getStorage());
    }
}

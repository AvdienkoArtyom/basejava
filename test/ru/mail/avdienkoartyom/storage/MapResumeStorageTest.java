package ru.mail.avdienkoartyom.storage;

import org.junit.Test;

public class MapResumeStorageTest extends AbstractStorageTest {
    public MapResumeStorageTest() {
        super(new MapResumeStorage());
    }

    @Test
    public void testArrayOverflow() {
    }
}

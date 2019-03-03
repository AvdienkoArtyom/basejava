package ru.mail.avdienkoartyom.storage;

import org.junit.Test;

public class MapUuidStorageTest extends AbstractStorageTest {
    public MapUuidStorageTest() {
        super(new MapUuidStorage());
    }

    @Test
    public void testArrayOverflow() {
    }
}

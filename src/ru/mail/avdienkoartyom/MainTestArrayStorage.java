package ru.mail.avdienkoartyom;

import ru.mail.avdienkoartyom.storage.ArrayStorage;
import ru.mail.avdienkoartyom.storage.MapResumeStorage;
import ru.mail.avdienkoartyom.storage.MapUuidStorage;
import ru.mail.avdienkoartyom.storage.Storage;
import ru.mail.avdienkoartyom.model.Resume;

/**
 * Test for your ru.mail.avdienkoartyom.Storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "Петр Петрович");
        Resume r2 = new Resume("uuid2", "Анатолий Анатольевич");
        Resume r3 = new Resume("uuid3", "Николай Николаевич");
        Resume r6 = new Resume("uuid6", "Сергей Сергеевич");
        Resume r5 = new Resume("uuid5", "Олег Олегович");
        Resume r4 = new Resume("uuid4", "Александр Александрович");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r6);
        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r4);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        //System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));


        ARRAY_STORAGE.update(r1);
        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();


        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}

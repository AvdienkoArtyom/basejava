package ru.mail.avdienkoartyom.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " exist.", uuid);
    }
}

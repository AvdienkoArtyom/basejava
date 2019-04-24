package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.exception.StorageException;
import ru.mail.avdienkoartyom.model.Resume;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {

    private File directory;
    private DiskStrategy diskStrategy;

    public FileStorage(File directory, DiskStrategy diskStrategy) {
        Objects.requireNonNull(diskStrategy, "diskStrategy must not be null");
        this.diskStrategy = diskStrategy;
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory.");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readeble/writeble.");
        }
        this.directory = directory;
    }

    public void doWrite(Resume resume, OutputStream outputStream) {
        diskStrategy.doWrite(resume, outputStream);
    }

    public Resume doRead(InputStream inputStream) {
        return diskStrategy.doRead(inputStream);
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, new FileOutputStream(file));
        } catch (IOException e) {
            throw new StorageException("IO error to save", file.getName(), e);
        }

    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error to get resume", file.getName(), e);
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        Resume[] resumes = new Resume[size()];
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Error, failed to get file list", null);
        }
        for (int i = 0; i < resumes.length; i++) {
            resumes[i] = doGet(files[i]);
        }
        return Arrays.asList(resumes);
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error to update", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("Delete error", file.getName());
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Error, failed to get file list", null);
        }
        for (File f : files) {
            doDelete(f);
        }
    }

    @Override
    public int size() {
        String[] files = directory.list();
        if (files == null) {
            throw new StorageException("Error, failed to get file list", null);
        }
        return files.length;
    }
}

package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.exception.StorageException;
import ru.mail.avdienkoartyom.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private File directory;

    public AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory.");
        }
        if (directory.canRead() || directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readeble/writeble.");
        }
        this.directory = directory;
    }

    protected abstract void doWrite(Resume resume, File file);

    protected abstract Resume doRead(File file);

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }

    }

    @Override
    protected Resume doGet(File file) {
        return doRead(file);
    }

    @Override
    protected List<Resume> doGetAll() {
        Resume[] resumes = new Resume[size()];
        File[] files = directory.listFiles();
        for (int i = 0; i < resumes.length; i++) {
            resumes[i] = doRead(files[i]);
        }
        return Arrays.asList(resumes);
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        doWrite(resume, file);
    }

    @Override
    protected void doDelete(File file) {
        for (File f : directory.listFiles()) {
            if (f.getName().equals(file.getName()))
                f.delete();
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
        for (File f : directory.listFiles()) {
            if (!f.isDirectory())
                f.delete();
        }
    }

    @Override
    public int size() {
        return directory.listFiles().length;
    }
}

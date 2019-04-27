package ru.mail.avdienkoartyom.storage;

import ru.mail.avdienkoartyom.exception.StorageException;
import ru.mail.avdienkoartyom.model.Resume;
import ru.mail.avdienkoartyom.storage.SorageStrategy.StorageStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathStorage extends AbstractStorage<Path> {

    private Path directory;
    private StorageStrategy storageStrategy;

    public PathStorage(Path path, StorageStrategy storageStrategy) {
        Objects.requireNonNull(storageStrategy, "storageStrategy must not be null");
        this.storageStrategy = storageStrategy;
        directory = path;
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(path + " is not directory.");
        }
        if (!Files.isReadable(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(path + " is not readeble/writeble.");
        }
        this.directory = directory;
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
            storageStrategy.doWrite(resume, new FileOutputStream(path.toFile()));
        } catch (IOException e) {
            throw new StorageException("IO error to save", path.toString(), e);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return storageStrategy.doRead(new BufferedInputStream(new FileInputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("IO error to get resume", path.toString(), e);
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        List<Resume> resumeList = new ArrayList<>();
        List<Path> pathList;
        try {
            pathList = Files.list(directory).collect(Collectors.toList());
            for (Path p : pathList) {
                resumeList.add(doGet(p));
            }
        } catch (IOException e) {
            throw new StorageException("Error, failed to get Path list", directory.toString(), e);
        }
        return resumeList;
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            storageStrategy.doWrite(resume, new BufferedOutputStream(new FileOutputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("IO error to update", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new StorageException("Delete error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        String[] paths = directory.toFile().list();
        if (paths == null) {
            throw new StorageException("Error, failed to get Path list", null);
        }
        return paths.length;
    }
}

package ru.mail.avdienkoartyom;

import ru.mail.avdienkoartyom.storage.SqlStorage;
import ru.mail.avdienkoartyom.storage.Storage;

import java.io.*;
import java.util.Properties;

public class Config {
    private static final String PROPS = "/resumes.properties";
    private static final Config INSTANCE = new Config();
    private final File storageDir;
    private final Storage storage;

    public static Config getInstance() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = Config.class.getResourceAsStream(PROPS)) {
            Properties properties = new Properties();
            properties.load(is);
            storageDir = new File(properties.getProperty("storage.dir"));
            storage = new SqlStorage(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS);

        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public Storage getStorage() {
        return storage;
    }


}

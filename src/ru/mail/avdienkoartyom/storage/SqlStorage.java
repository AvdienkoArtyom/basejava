package ru.mail.avdienkoartyom.storage;


import ru.mail.avdienkoartyom.exception.NoExistStorageException;
import ru.mail.avdienkoartyom.model.ContactType;
import ru.mail.avdienkoartyom.model.Resume;
import ru.mail.avdienkoartyom.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {

    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            insertContact(connection, resume);
            return null;
        });

    }

    public void insertContact(Connection connection, Resume resume) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : resume.getContact().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Resume resume) {
        if (get(resume.getUuid()) == null) {
            throw new NoExistStorageException(resume.getUuid());
        } else {

            sqlHelper.transactionExecute(connection -> {
                try (PreparedStatement ps = connection.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                    ps.setString(1, resume.getFullName());
                    ps.setString(2, resume.getUuid());
                    if (ps.executeUpdate() == 0) {
                        throw new NoExistStorageException(resume.getUuid());
                    }
                }

                try (PreparedStatement ps = connection.prepareStatement("DELETE FROM contact WHERE contact.resume_uuid = ?")) {
                    ps.setString(1, resume.getUuid());
                    if (ps.executeUpdate() == 0) {
                        throw new NoExistStorageException(resume.getUuid());
                    }
                }
                insertContact(connection, resume);
                return null;
            });

            delete(resume.getUuid());
            save(resume);
        }
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("" +
                        " SELECT * " +
                        "     FROM resume r " +
                        "LEFT JOIN  contact c " +
                        "       ON r.uuid = c.resume_uuid " +
                        "    WHERE r.uuid=?",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NoExistStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    do {
                        String ct = rs.getString("type");
                        if (ct != null) {
                            String value = rs.getString("value");
                            resume.getContact().put(ContactType.valueOf(ct), value);
                        }
                    } while (rs.next());
                    return resume;
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume r LEFT OUTER JOIN contact ON (uuid = resume_uuid) ORDER BY full_name, uuid;", ps -> {
            ResultSet resultSet = ps.executeQuery();

            Map<String, Resume> resumeMap = new LinkedHashMap<>();
            while (resultSet.next()) {
                String uuid = resultSet.getString("uuid");
                String full_name = resultSet.getString("full_name");
                resumeMap.computeIfAbsent(uuid, f -> new Resume(uuid, full_name));
                String ct = resultSet.getString("type");
                if (ct != null) {
                    String value = resultSet.getString("value");
                    resumeMap.get(uuid).getContact().put(ContactType.valueOf(ct), value);
                }
            }
            return new ArrayList<>(resumeMap.values());
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.<Void>execute("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NoExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", ps -> {
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next() ? resultSet.getInt(1) : 0;
        });
    }
}

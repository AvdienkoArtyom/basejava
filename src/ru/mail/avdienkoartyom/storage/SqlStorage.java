package ru.mail.avdienkoartyom.storage;


import ru.mail.avdienkoartyom.exception.NoExistStorageException;
import ru.mail.avdienkoartyom.model.*;
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
            insertSection(connection, resume);
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
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
                ps.executeUpdate();
            }
            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM section WHERE section.resume_uuid = ?")) {
                ps.setString(1, resume.getUuid());
                ps.executeUpdate();
            }
            insertContact(connection, resume);
            insertSection(connection, resume);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionExecute(connection -> {
            Resume resume;
            try (PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM resume r LEFT JOIN contact c on r.uuid = c.resume_uuid WHERE r.uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NoExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));
                do {
                    getContact(resume, rs);
                } while (rs.next());
            }
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM section WHERE section.resume_uuid = ?")) {
                ps.setString(1, resume.getUuid());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    getSection(resume, rs);
                }
            }
            return resume;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        Map<String, Resume> resumeMap = new LinkedHashMap<>();
        return sqlHelper.transactionExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid")) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    String uuid = resultSet.getString("uuid");
                    String full_name = resultSet.getString("full_name");
                    resumeMap.put(uuid, new Resume(uuid, full_name));
                }
            }
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM contact")) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    String resume_uuid = resultSet.getString("resume_uuid");
                    getContact(resumeMap.get(resume_uuid), resultSet);
                }
            }

            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM section")) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    String resume_uuid = resultSet.getString("resume_uuid");
                    getSection(resumeMap.get(resume_uuid), resultSet);
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

    private void getContact(Resume resume, ResultSet rs) throws SQLException {
        String ct = rs.getString("type");
        if (ct != null) {
            String value = rs.getString("value");
            resume.getContact().put(ContactType.valueOf(ct), value);
        }
    }

    private void getSection(Resume resume, ResultSet rs) throws SQLException {
        String st = rs.getString("section_type");
        if (st != null) {
            SectionType sectionType = SectionType.valueOf(st);
            switch (sectionType) {
                case PERSONAL:
                case OBJECTIVE:
                    resume.getSection().put(sectionType, new SimpleTextSection(rs.getString("description")));
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    resume.getSection().put(sectionType, new ListSection(Arrays.asList(rs.getString("description").split("\n"))));
                    break;
            }
        }
    }

    private void insertSection(Connection connection, Resume resume) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO section (resume_uuid, section_type, description) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, AbstractSection> sectionEntry : resume.getSection().entrySet()) {
                SectionType sectionType = sectionEntry.getKey();
                AbstractSection abstractSection = sectionEntry.getValue();
                ps.setString(1, resume.getUuid());
                ps.setString(2, sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        ps.setString(3, ((SimpleTextSection) abstractSection).getDescription());
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ps.setString(3, abstractSection.toString());
                }
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertContact(Connection connection, Resume resume) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            ps.setString(1, resume.getUuid());
            for (Map.Entry<ContactType, String> e : resume.getContact().entrySet()) {
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}

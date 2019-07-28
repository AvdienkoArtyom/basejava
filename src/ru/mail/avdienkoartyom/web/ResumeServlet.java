package ru.mail.avdienkoartyom.web;

import ru.mail.avdienkoartyom.Config;
import ru.mail.avdienkoartyom.TestDataResume;
import ru.mail.avdienkoartyom.model.*;
import ru.mail.avdienkoartyom.storage.SortedArrayStorage;
import ru.mail.avdienkoartyom.storage.Storage;
import sun.swing.SwingUtilities2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;


public class ResumeServlet extends javax.servlet.http.HttpServlet {

    private Storage storage;

    @Override
    public void init() throws ServletException {
        storage = Config.getInstance().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        String action = req.getParameter("action");

        if (action == null) {
            req.setAttribute("resumes", storage.getAllSorted());
            req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                resp.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                if (uuid.equals("newResume")) {
                    r = new Resume();
                } else {
                    r = storage.get(uuid);
                }
                break;
            default:
                throw new IllegalStateException("Action " + action + " is illegal");
        }
        req.setAttribute("resume", r);
        req.getRequestDispatcher("view".equals(action) ? "WEB-INF/jsp/view.jsp" : "WEB-INF/jsp/edit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String uuid = req.getParameter("uuid");
        String fullName = req.getParameter("fullName");
        if (fullName != null && fullName.trim().length() != 0) {
            Resume resume;
            if (uuid.isEmpty()) {
                resume = new Resume(fullName);
                addContact(resume, req, resp);
                addSection(resume, req, resp);
                storage.save(resume);
            } else {
                resume = storage.get(uuid);
                resume.setFullName(fullName);
                addContact(resume, req, resp);
                addSection(resume, req, resp);
                storage.update(resume);
            }
        }
        resp.sendRedirect("resume");
    }

    private void addContact(Resume resume, HttpServletRequest req, HttpServletResponse resp) {
        for (ContactType type : ContactType.values()) {
            String value = req.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                resume.getContact().put(type, value);
            } else {
                resume.getContact().remove(type);
            }
        }
    }

    private void addSection(Resume resume, HttpServletRequest req, HttpServletResponse resp) {
        for (SectionType type : SectionType.values()) {
            String value = req.getParameter(type.name());
            String[] values = req.getParameterValues(type.name());
            if (values != null && values.length != 0) {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.getSection().put(type, new SimpleTextSection(value));
                        break;
                    case QUALIFICATIONS:
                    case ACHIEVEMENT:
                        resume.getSection().put(type, new ListSection(Arrays.asList(values)));
                }

            } else {
                resume.getSection().remove(type);
            }
        }
    }
}

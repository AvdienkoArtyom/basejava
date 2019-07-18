package ru.mail.avdienkoartyom.web;

import ru.mail.avdienkoartyom.TestDataResume;
import ru.mail.avdienkoartyom.model.Resume;
import ru.mail.avdienkoartyom.storage.SortedArrayStorage;
import ru.mail.avdienkoartyom.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class ResumeServlet extends javax.servlet.http.HttpServlet {

    private Storage storage;
    private List<Resume> resumeList;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        StringBuilder stringResponse = new StringBuilder("<table align=\"center\" width=80% border=2>");
        for (Resume resume : resumeList) {
            stringResponse.append("<tr>");
            stringResponse.append("<td> Полное имя:" + resume.getFullName() + "</td>");
            stringResponse.append("<td> UUID:" + resume.getUuid() + "</td>");
            stringResponse.append("</tr>");
            stringResponse.append("<tr>");
        }
        stringResponse.append("</table>");
        resp.getWriter().write(stringResponse.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        storage = new SortedArrayStorage();
        storage.save(TestDataResume.createResumeUUID(UUID.randomUUID().toString(), "Петр_1"));
        storage.save(TestDataResume.createResumeUUIDWithOneContact(UUID.randomUUID().toString(), "Петр_2"));
        storage.save(TestDataResume.createResumeUUIDWithoutContact(UUID.randomUUID().toString(), "Петр_3"));
        resumeList = storage.getAllSorted();
    }
}

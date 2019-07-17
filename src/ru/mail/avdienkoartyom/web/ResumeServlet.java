package ru.mail.avdienkoartyom.web;

import ru.mail.avdienkoartyom.TestDataResume;
import ru.mail.avdienkoartyom.model.AbstractSection;
import ru.mail.avdienkoartyom.model.ContactType;
import ru.mail.avdienkoartyom.model.Resume;
import ru.mail.avdienkoartyom.model.SectionType;
import ru.mail.avdienkoartyom.storage.SortedArrayStorage;
import ru.mail.avdienkoartyom.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ResumeServlet extends javax.servlet.http.HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Storage storage = new SortedArrayStorage();
        storage.save(TestDataResume.createResumeUUID(UUID.randomUUID().toString(), "Петр_1"));
        storage.save(TestDataResume.createResumeUUIDWithOneContact(UUID.randomUUID().toString(), "Петр_2"));
        storage.save(TestDataResume.createResumeUUIDWithoutContact(UUID.randomUUID().toString(), "Петр_3"));

        List<Resume> resumeList = storage.getAllSorted();
        StringBuilder stringResponse = new StringBuilder("<table align=\"center\" width=80% border=2>");
        for (Resume resume : resumeList) {
            stringResponse.append("<tr>");
            stringResponse.append("<td> Полное имя:" + resume.getFullName() + "</td>");
            stringResponse.append("<td> UUID:" + resume.getUuid() + "</td>");
            stringResponse.append("</tr>");
            stringResponse.append("<tr>");

            if (resume.getContact().isEmpty()) {
                stringResponse.append("<td colspan=\"2\">Нет контактов</td>");
            } else {
                StringBuilder sbContact = new StringBuilder("<ul>");

                for (Map.Entry<ContactType, String> entry : resume.getContact().entrySet()) {
                    sbContact.append("<li>" + entry.getKey().getTitle() + " " + entry.getValue() + "</li>");
                }
                sbContact.append("</ul>");

                stringResponse.append("<td colspan=\"2\">" + sbContact + "</td>");
            }
            stringResponse.append("</tr>");
            stringResponse.append("<tr>");
            StringBuilder sbSection = new StringBuilder("<ul>");
            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSection().entrySet()) {
                sbSection.append("<li>" + entry.getKey().getTitle() + " " + entry.getValue() + "</li>");
            }
            sbSection.append("</ul>");
            stringResponse.append("<td colspan=\"3\">" + sbSection + "</td>");
            stringResponse.append("</tr>");
        }
        stringResponse.append("</table>");
        resp.getWriter().write(stringResponse.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

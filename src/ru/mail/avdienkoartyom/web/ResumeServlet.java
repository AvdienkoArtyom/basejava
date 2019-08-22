package ru.mail.avdienkoartyom.web;

import ru.mail.avdienkoartyom.Config;
import ru.mail.avdienkoartyom.model.*;
import ru.mail.avdienkoartyom.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


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
                r = storage.get(uuid);
                break;
            case "add":
                r = Resume.EMPTY;
                break;
            case "edit":
                r = storage.get(uuid);
                for (SectionType type : SectionType.values()) {
                    AbstractSection abstractSection = r.getSection(type);
                    switch (type) {
                        case OBJECTIVE:
                        case PERSONAL:
                            if (abstractSection == null) {
                                abstractSection = SimpleTextSection.EMPTY;
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (abstractSection == null) {
                                abstractSection = ListSection.EMPTY;
                            }
                            break;
                        case EXPERIENCE:
                        case EDUCATION:
                            OrganizationSection organizationSection = (OrganizationSection) abstractSection;
                            List<Organization> emptyFirstOrganizations = new ArrayList<>();
                            emptyFirstOrganizations.add(Organization.EMPTY);
                            if (organizationSection != null) {
                                for (Organization organization : organizationSection.getOrganizationList()) {
                                    List<Position> emptyFirstPositions = new ArrayList<>();
                                    emptyFirstPositions.addAll(organization.getPositionList());
                                    emptyFirstPositions.add(Position.EMPTY);
                                    emptyFirstOrganizations.add(new Organization(organization.getTitle(), organization.getUrl(), emptyFirstPositions));
                                }
                            }
                            abstractSection = new OrganizationSection(emptyFirstOrganizations);
                            break;
                    }
                    r.setSection(type, abstractSection);
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
            boolean isNew = uuid.length() == 0;
            if (isNew) {
                resume = new Resume(fullName);
            } else {
                resume = storage.get(uuid);
                resume.setFullName(fullName);
            }
            addContact(resume, req, resp);
            addSection(resume, req, resp);
            if (isNew) {
                storage.save(resume);
            } else {
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
            String[] titles = req.getParameterValues(type.name());
            String[] urls = req.getParameterValues(type.name() + "url");
            String title;
            String url;
            List<Position> positionList;
            List<Organization> organizationList;
            switch (type) {
                case OBJECTIVE:
                case PERSONAL:
                    resume.getSection().put(type, new SimpleTextSection(value));
                    break;
                case QUALIFICATIONS:
                case ACHIEVEMENT:
                    if (value != null && value.trim().length() != 0) {
                        resume.getSection().put(type, new ListSection(Arrays.asList(value.trim().split("\r\n"))));
                    } else {
                        resume.getSection().put(type, ListSection.EMPTY);
                    }
                    break;
                case EDUCATION:
                case EXPERIENCE:
                    organizationList = new ArrayList<>();
                    int count = 1;
                    for (int i = 0; i < titles.length; i++) {
                        title = titles[i];
                        if (title.trim().length() != 0) {
                            url = urls[i];
                            positionList = new ArrayList<>();
                            String[] startDates = req.getParameterValues(count + "positionStart" + type.name());
                            String[] finishes = req.getParameterValues(count + "positionFinish" + type.name());
                            String[] statuses = req.getParameterValues(count + "positionStatus" + type.name());
                            String[] descriptions = req.getParameterValues(count + "positionDescription" + type.name());
                            for (int j = 0; j < startDates.length; j++) {
                                if (descriptions[j].trim().length() != 0) {
                                    String status = statuses[j].trim();
                                    LocalDate start = dateParse(startDates[j]);
                                    LocalDate finish = dateParse(finishes[j]);
                                    String description = descriptions[j];
                                    positionList.add(new Position(start, finish, status, description));
                                }
                            }
                            organizationList.add(new Organization(title, url, positionList));
                        }
                        count++;
                    }
                    resume.setSection(type, new OrganizationSection(organizationList));
                    break;
            }
        }
    }

    private LocalDate dateParse(String date) {
        if (date == null || date.trim().length() == 0) {
            return null;
        } else {
            String[] stringArr = date.split("[-.,:]");
            return LocalDate.of(Integer.parseInt(stringArr[0]), Integer.parseInt(stringArr[1]), Integer.parseInt(stringArr[2]));
        }
    }
}

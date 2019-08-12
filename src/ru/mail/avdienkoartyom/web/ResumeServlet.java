package ru.mail.avdienkoartyom.web;

import ru.mail.avdienkoartyom.Config;
import ru.mail.avdienkoartyom.model.*;
import ru.mail.avdienkoartyom.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


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
                r = uuid.equals("newResume") ? new Resume() : storage.get(uuid);
                for (SectionType type : new SectionType[]{SectionType.EXPERIENCE, SectionType.EDUCATION})
                    if (r.getSection().get(type) == null) {
                        r.getSection().put(type, new OrganizationSection(Collections.emptyList()));
                    }
                break;
            default:
                throw new IllegalStateException("Action " + action + " is illegal");
        }
        req.setAttribute("organizationExp", (OrganizationSection) r.getSection().get(SectionType.EXPERIENCE));
        req.setAttribute("organizationEdu", (OrganizationSection) r.getSection().get(SectionType.EDUCATION));
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
            boolean isNew = uuid.isEmpty();
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
                    resume.getSection().put(type, new ListSection(Arrays.asList(value.split("\r\n"))));
                    break;
                case EXPERIENCE:
                    organizationList = new ArrayList<>();
                    int countExp = 1;
                    while (true) {
                        title = req.getParameter(countExp + "titleExp");
                        if (title == null || title.trim().length() == 0) {
                            break;
                        }
                        url = req.getParameter(countExp + "urlExp");
                        int countPosExp = 1;
                        positionList = new ArrayList<>();
                        while (true) {
                            LocalDate start = dateParse(req.getParameter(countExp + "positionStartExp" + countPosExp));
                            if (start == null) {
                                break;
                            }
                            LocalDate finish = dateParse(req.getParameter(countExp + "positionFinishExp" + countPosExp));
                            String status = req.getParameter(countExp + "positionStatusExp" + countPosExp);
                            String description = req.getParameter(countExp + "positionDescriptionExp" + countPosExp);
                            positionList.add(new Position(start, finish, status, description));
                            countPosExp++;
                        }
                        organizationList.add(new Organization(title, url, positionList));
                        countExp++;
                    }
                    String newTitleExp = req.getParameter("titleExp");
                    if (newTitleExp.trim().length() != 0) {
                        addNewEducationExperience(newTitleExp, req, organizationList);
                    }
                    resume.getSection().put(SectionType.EXPERIENCE, new OrganizationSection(organizationList));
                    break;

                case EDUCATION:
                    organizationList = new ArrayList<>();
                    int countEdu = 1;
                    while (true) {
                        title = req.getParameter(countEdu + "titleEdu");
                        if (title == null || title.trim().length() == 0) {
                            break;
                        }
                        url = req.getParameter(countEdu + "urlEdu");
                        int countPosEdu = 1;
                        positionList = new ArrayList<>();
                        while (true) {
                            LocalDate start = dateParse(req.getParameter(countEdu + "positionStartEdu" + countPosEdu));
                            if (start == null) {
                                break;
                            }
                            LocalDate finish = dateParse(req.getParameter(countEdu + "positionFinishEdu" + countPosEdu));
                            String description = req.getParameter(countEdu + "positionDescriptionEdu" + countPosEdu);
                            positionList.add(new Position(start, finish, description));
                            countPosEdu++;
                        }
                        organizationList.add(new Organization(title, url, positionList));

                        countEdu++;
                    }
                    String newTitleEdu = req.getParameter("titleEdu");
                    if (newTitleEdu.trim().length() != 0) {
                        addNewEducationExperience(newTitleEdu, req, organizationList);
                    }
                    resume.getSection().put(SectionType.EDUCATION, new OrganizationSection(organizationList));
                    break;
            }
        }
    }

    private LocalDate dateParse(String date) {
        if (date == null || date.trim().length() == 0) {
            return null;
        } else {
            String[] stringArr = date.split("[-.,:]");
            return LocalDate.of(Integer.valueOf(stringArr[0]), Integer.valueOf(stringArr[1]), Integer.valueOf(stringArr[2]));
        }
    }

    private void addNewEducationExperience(String newTitle, HttpServletRequest req, List<Organization> organizationSectionList) {
        String newUrl = req.getParameter("urlEdu");
        LocalDate start = dateParse(req.getParameter("positionStartEdu"));
        LocalDate finish = dateParse(req.getParameter("positionFinishEdu"));
        String description = req.getParameter("positionDescriptionEdu");
        List<Position> newPositionList = new ArrayList<>();
        String status = req.getParameter("positionStatusExp");
        if (status.trim().length() != 0) {
            newPositionList.add(new Position(start, finish, status, description));
        } else {
            newPositionList.add(new Position(start, finish, description));
        }
        organizationSectionList.add(new Organization(newTitle, newUrl, newPositionList));
    }
}

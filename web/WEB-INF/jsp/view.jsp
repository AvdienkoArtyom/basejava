<%@ page import="ru.mail.avdienkoartyom.util.ToHtmlContact" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.mail.avdienkoartyom.model.*" %>
<%@ page import="ru.mail.avdienkoartyom.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html;" charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.mail.avdienkoartyom.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"></jsp:include>
<section id="viewResume">
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit">Edit</a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contact}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.mail.avdienkoartyom.model.ContactType, java.lang.String>"/>

            <%=ToHtmlContact.toHtml(contactEntry.getKey(), contactEntry.getValue())%></br>

        </c:forEach>
    </p>
    <hr>
    <table cellpadding="2">
        <c:forEach var="sectionEntry" items="${resume.section}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<ru.mail.avdienkoartyom.model.SectionType, ru.mail.avdienkoartyom.model.AbstractSection>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="ru.mail.avdienkoartyom.model.AbstractSection"/>
            <c:choose>
                <c:when test="${type=='OBJECTIVE'}">
                    <c:if test="<%=((SimpleTextSection) section).getDescription().trim().length()!=0%>">
                        <tr>
                            <td colspan="2"><h2><a name="type.name">${type.title}</a></h2></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <h4><%=((SimpleTextSection) section).getDescription()%>
                                </h4>
                            </td>
                        </tr>
                    </c:if>
                </c:when>
                <c:when test="${type=='PERSONAL'}">
                    <c:if test="<%=((SimpleTextSection) section).getDescription().trim().length()!=0%>">
                        <tr>
                            <td colspan="2"><h2><a name="type.name">${type.title}</a></h2></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <%=((SimpleTextSection) section).getDescription()%>
                            </td>
                        </tr>
                    </c:if>
                </c:when>
                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                    <c:if test="<%=!((ListSection) section).isEmptyList()%>">
                        <tr>
                            <td colspan="2"><h2><a name="type.name">${type.title}</a></h2></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <ul>
                                    <c:forEach var="item" items="<%=((ListSection) section).getList()%>">
                                        <li>${item}</li>
                                    </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </c:if>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:if test="<%=!((OrganizationSection) section).getOrganizationList().isEmpty()%>">
                        <tr>
                            <td colspan="2"><h2><a name="type.name">${type.title}</a></h2></td>
                        </tr>
                        <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizationList()%>">
                            <tr>
                                <td colspan="2">
                                    <c:choose>
                                        <c:when test="${empty org.url}">
                                            <h2>${org.title}</h2>
                                        </c:when>
                                        <c:otherwise>
                                            <h2><a href="${org.url}">${org.title}</a></h2>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <c:forEach var="position" items="${org.positionList}">
                                <jsp:useBean id="position" type="ru.mail.avdienkoartyom.model.Position "/>
                                <tr>
                                    <td width="15%"><%=HtmlUtil.formatDates(position)%>
                                    </td>
                                    <td id="statusDescription"><b>${position.status}</b><br>${position.description}</td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </c:if>
                </c:when>
            </c:choose>
        </c:forEach>
    </table>
    <br/>
    <button onclick="window.history.back()">К списку резюме</button>
</section>
<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>

<%@ page import="ru.mail.avdienkoartyom.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.mail.avdienkoartyom.model.ContactType" %>
<%@ page import="ru.mail.avdienkoartyom.util.ToHtmlContact" %>
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
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit">Edit</a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contact}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.mail.avdienkoartyom.model.ContactType, java.lang.String>"/>
            <%=ToHtmlContact.toHtml(contactEntry.getKey(), contactEntry.getValue())%></br>
        </c:forEach>
    </p>
    <c:forEach var="sectionEntry" items="${resume.section}">
        <jsp:useBean id="sectionEntry"
                     type="java.util.Map.Entry<ru.mail.avdienkoartyom.model.SectionType, ru.mail.avdienkoartyom.model.AbstractSection>"/>
        <h4><%=sectionEntry.getKey()%>
        </h4>
        <p><%=sectionEntry.getValue().toString().replaceAll("\n", "</br>")%>></p>
    </c:forEach>

</section>
<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>

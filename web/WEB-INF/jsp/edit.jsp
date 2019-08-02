<%@ page import="ru.mail.avdienkoartyom.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.mail.avdienkoartyom.model.ContactType" %>
<%@ page import="ru.mail.avdienkoartyom.model.SectionType" %>
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
    <form id="addForm" method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd>
                <input type="text" name="fullName" size=30 value="${resume.fullName}">
            </dd>
        </dl>
        <p>
        <h3>Контакты</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd>
                    <input type="text" name="${type.name()}" size=30 value="${resume.contact.get(type)}">
                </dd>
            </dl>
        </c:forEach>
        </p>
        <p>
            <c:forEach var="type" items="<%=SectionType.values()%>">
        <dl>
            <dt><h3>${type.title}</h3></dt>
            <c:if test="${type.name()=='PERSONAL'||type.name()=='OBJECTIVE'}">
                <br>
                <input type="text" name="${type.name()}" size="56" value="${resume.section.get(type)}"><br>
            </c:if>
            <c:if test="${type.name()=='ACHIEVEMENT'||type.name()=='QUALIFICATIONS'}">
                <br>
                <textarea type="text" rows="10" cols="45" name="${type.name()}">${resume.section.get(type)}
                </textarea>
            </c:if>
            <c:if test="${type.name()=='EXPERIENCE'||type.name()=='EDUCATION'}">
                <br>
                <textarea type="text" rows="10" cols="45" name="${type.name()}" readonly>${resume.section.get(type)}
                </textarea>
            </c:if>
            </dd>
        </dl>
        </c:forEach>
        </p>
        <hr>
    </form>
    <button form="addForm" type="submit">Сохранить</button>
    <button form="addForm" onclick="window.history.back()">Отменить</button>
</section>
<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>

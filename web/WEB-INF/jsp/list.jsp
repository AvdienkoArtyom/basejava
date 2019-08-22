<%@ page import="ru.mail.avdienkoartyom.model.ContactType" %>
<%@ page import="ru.mail.avdienkoartyom.util.ToHtmlContact" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html;" charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список всех резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"></jsp:include>
<section>
        <table id="tableListResume" border="3" cellspacing="0" cellpadding="10">
            <tr>
                <th>Имя:</th>
                <th>Email:</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${resumes}" var="resume">
                <jsp:useBean id="resume" type="ru.mail.avdienkoartyom.model.Resume"/>
                <tr>
                    <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                    <td><%=ToHtmlContact.toHtml(ContactType.EMAIL, resume.getContact().get(ContactType.EMAIL))%>
                    </td>
                    <td><a href="resume?uuid=${resume.uuid}&action=delete">Delete</a></td>
                    <td><a href="resume?uuid=${resume.uuid}&action=edit">Edit</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="4"><a href="resume?uuid=${"newResume"}&action=add">Добавить новое резме.</a></td>
            </tr>
        </table>
</section>
<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>

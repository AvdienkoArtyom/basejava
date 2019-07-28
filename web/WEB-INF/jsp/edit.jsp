<%@ page import="ru.mail.avdienkoartyom.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.mail.avdienkoartyom.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
       <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
           <input type="hidden" name="uuid" value="${resume.uuid}">
           <dl>
               <dt>Имя:</dt>
               <dd>
                   <input type="text" name="fullName" size=50 value="${resume.fullName}">
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

           </p>
           <hr>
           <button type="submit">Сохранить</button>
           <button onclick="window.history.back()">Отменить</button>
       </form>
</section>
<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>

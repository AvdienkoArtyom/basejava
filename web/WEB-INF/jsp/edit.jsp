<%@ page import="ru.mail.avdienkoartyom.model.*" %>
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
        <h3>Контакты</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd>
                    <input type="text" name="${type.name()}" size=30 value="${resume.contact.get(type)}">
                </dd>
            </dl>
        </c:forEach>
        <hr>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="abstractSection" value="${resume.section.get(type)}"/>
            <jsp:useBean id="abstractSection" type="ru.mail.avdienkoartyom.model.AbstractSection"/>
            <h2><a>${type.title}</a></h2>
            <c:choose>
                <c:when test="${type=='OBJECTIVE'}">
                    <input type="text" name="${type}" size=75 value="${resume.section.get(type)}" placeholder="Позиция">
                </c:when>
                <c:when test="${type=='PERSONAL'}">
                    <textarea name='${type}' cols=75 rows=5 placeholder="Личные качества">${resume.section.get(type)}</textarea>
                </c:when>
                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                <textarea name='${type}' cols=75
                          rows=5>${resume.section.get(type)}</textarea>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="organization"
                               items="<%=((OrganizationSection) abstractSection).getOrganizationList()%>"
                               varStatus="counter">
                        <c:if test="${empty organization.title}">
                            <p>
                            <h3>добавить организацию</h3></p>
                        </c:if>
                        <c:if test="${!empty organization.title}">
                            <p>
                            <h3>редактировать организацию</h3></p>
                        </c:if>

                        <dl>
                            <dt>Название организации:</dt>
                            <dd><input type="text" name="${type}" size="56"
                                       value="${organization.title}"></dd>
                        </dl>
                        <dl>
                            <dt>сайт оргаизации:</dt>
                            <dd><input type="text" name="${type}url" size="56"
                                       value="${organization.url}"></dd>
                        </dl>
                        <div style="margin-left: 30px">
                            <c:forEach var="position" items="${organization.positionList}">
                                <c:if test="${empty position.dateStart}">
                                    <p><h4>добавить позицию</h4></p>
                                </c:if>
                                <c:if test="${!empty position.dateStart}">
                                    <p><h4>редактировать позицию</h4></p>
                                </c:if>
                                <dl>
                                    <dt>начало карьеры:</dt>
                                    <dd><input type="text"
                                               name="${counter.count}positionStart${type}"
                                               size="56"
                                               value="${position.dateStart}" placeholder="YYYY-MM-DD"> </dd>
                                </dl>
                                <dl>
                                    <dt>окончание карьеры:</dt>
                                    <dd><input type="text"
                                               name="${counter.count}positionFinish${type}"
                                               size="56"
                                               value="${position.dateFinish}" placeholder="YYYY-MM-DD"></dd>
                                </dl>
                                <dl>
                                    <dt>должность:</dt>
                                    <dd><input type="text"
                                               name="${counter.count}positionStatus${type}"
                                               size="56"
                                               value="${position.status}"></dd>
                                </dl>
                                <dl>
                                    <dt>описание:</dt>
                                    <dd><input type="text"
                                               name="${counter.count}positionDescription${type}"
                                               size="56"
                                               value="${position.description}"></dd>
                                </dl>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
        <hr>
    </form>
    <button form="addForm" type="submit">Сохранить</button>
    <button onclick="window.history.back()">Отменить</button>
</section>
<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>

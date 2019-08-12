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
        <h3>Контакты</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd>
                    <input type="text" name="${type.name()}" size=30 value="${resume.contact.get(type)}">
                </dd>
            </dl>
        </c:forEach>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <jsp:useBean id="organizationExp" type="ru.mail.avdienkoartyom.model.OrganizationSection" scope="request"/>
            <jsp:useBean id="organizationEdu" type="ru.mail.avdienkoartyom.model.OrganizationSection" scope="request"/>
            <c:choose>
                <c:when test="${type.name()=='PERSONAL'||type.name()=='OBJECTIVE'}">
                    <dl>
                        <dt>
                            <h3>${type.name()}</h3>
                        </dt>
                        <dd>
                            <input type="text" name="${type.name()}" size="56" value="${resume.section.get(type)}"><br>
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${type.name()=='ACHIEVEMENT'||type.name()=='QUALIFICATIONS'}">
                    <dl>
                        <dt>
                            <h3>${type.name()}</h3>
                        </dt>
                        <dd>
                            <textarea type="text" rows="10" cols="45"
                                      name="${type.name()}">${resume.section.get(type)}</textarea>
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${type=='EXPERIENCE'}">
                    <h3>${type.name()}</h3>
                    <c:if test="${organizationExp.organizationList.size()!=0}">
                        <c:forEach var="organization"
                                   items="${organizationExp.organizationList}"
                                   varStatus="counter">
                            <dl>
                                <dt>
                                    Название организации:
                                </dt>
                                <dd>
                                    <input type="text" name="${counter.count}titleExp" size="56"
                                           value="${organization.title}">
                                </dd>
                            </dl>
                            <dl>
                                <dt>
                                    сайт оргаизации:
                                </dt>
                                <dd>
                                    <input type="text" name="${counter.count}urlExp" size="56"
                                           value="${organization.url}">
                                </dd>
                            </dl>
                            <c:forEach var="position" items="${organization.positionList}"
                                       varStatus="counterPosition">
                                <dl>
                                    <dt>
                                        начало карьеры:
                                    </dt>
                                    <dd>
                                        <input type="text"
                                               name="${counter.count}positionStartExp${counterPosition.count}" size="56"
                                               value="${position.dateStart}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>
                                        окончание карьеры:
                                    </dt>
                                    <dd>
                                        <input type="text"
                                               name="${counter.count}positionFinishExp${counterPosition.count}"
                                               size="56"
                                               value="${position.dateFinish}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>
                                        должность:
                                    </dt>
                                    <dd>
                                        <input type="text"
                                               name="${counter.count}positionStatusExp${counterPosition.count}"
                                               size="56"
                                               value="${position.status}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>
                                        описание:
                                    </dt>
                                    <dd>
                                        <input type="text"
                                               name="${counter.count}positionDescriptionExp${counterPosition.count}"
                                               size="56"
                                               value="${position.description}">
                                    </dd>
                                </dl>
                            </c:forEach>
                        </c:forEach>
                    </c:if>
                    <h4>Добавить организацию:</h4>
                    <dl>
                        <dt>
                            Название организации:
                        </dt>
                        <dd>
                            <input type="text" name="titleExp" size="56" value="">
                        </dd>
                    </dl>
                    <dl>
                        <dt>
                            сайт оргаизации:
                        </dt>
                        <dd>
                            <input type="text" name="urlExp" size="56" value=""><br>
                        </dd>
                    </dl>
                    <dl>
                        <dt>
                            начало карьеры:
                        </dt>
                        <dd>
                            <input type="text" name="positionStartExp" size="56" placeholder="год-месяц-день" value="">
                        </dd>
                    </dl>
                    <dl>
                        <dt>
                            окончание карьеры:
                        </dt>
                        <dd>
                            <input type="text" name="positionFinishExp" size="56" placeholder="год-месяц-день" value="">
                        </dd>
                    </dl>
                    <dl>
                        <dt>
                            должность:
                        </dt>
                        <dd>
                            <input type="text" name="positionStatusExp" size="56" value="">
                        </dd>
                    </dl>
                    <dl>
                        <dt>
                            описание:
                        </dt>
                        <dd>
                            <input type="text" name="positionDescriptionExp" size="56" value="">
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${type=='EDUCATION'}">
                    <h3>${type.name()}</h3>
                    <c:if test="${organizationEdu.organizationList.size()!=0}">
                        <c:forEach var="organization"
                                   items="${organizationEdu.organizationList}"
                                   varStatus="counter">
                            <dl>
                                <dt>
                                    Название организации:
                                </dt>
                                <dd>
                                    <input type="text" name="${counter.count}titleEdu" size="56"
                                           value="${organization.title}">
                                </dd>
                            </dl>
                            <dl>
                                <dt>
                                    сайт оргаизации:
                                </dt>
                                <dd>
                                    <input type="text" name="${counter.count}urlEdu" size="56"
                                           value="${organization.url}">
                                </dd>
                            </dl>
                            <c:forEach var="position" items="${organization.positionList}"
                                       varStatus="counterPosition">
                                <dl>
                                    <dt>
                                        начало карьеры:
                                    </dt>
                                    <dd>
                                        <input type="text"
                                               name="${counter.count}positionStartEdu${counterPosition.count}" size="56"
                                               value="${position.dateStart}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>
                                        окончание карьеры:
                                    </dt>
                                    <dd>
                                        <input type="text"
                                               name="${counter.count}positionFinishEdu${counterPosition.count}"
                                               size="56"
                                               value="${position.dateFinish}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>
                                        описание:
                                    </dt>
                                    <dd>
                                        <input type="text"
                                               name="${counter.count}positionDescriptionEdu${counterPosition.count}"
                                               size="56"
                                               value="${position.description}">
                                    </dd>
                                </dl>
                            </c:forEach>
                        </c:forEach>
                    </c:if>
                    <h4>Добавить организацию:</h4>
                    <dl>
                        <dt>
                            Название организации:
                        </dt>
                        <dd>
                            <input type="text" name="titleEdu" size="56" value="">
                        </dd>
                    </dl>
                    <dl>
                        <dt>
                            сайт оргаизации:
                        </dt>
                        <dd>
                            <input type="text" name="urlEdu" size="56" value=""><br>
                        </dd>
                    </dl>
                    <dl>
                        <dt>
                            начало карьеры:
                        </dt>
                        <dd>
                            <input type="text" name="positionStartEdu" size="56" placeholder="год-месяц-день" value="">
                        </dd>
                    </dl>
                    <dl>
                        <dt>
                            окончание карьеры:
                        </dt>
                        <dd>
                            <input type="text" name="positionFinishEdu" size="56" placeholder="год-месяц-день" value="">
                        </dd>
                    </dl>

                    <dl>
                        <dt>
                            описание:
                        </dt>
                        <dd>
                            <input type="text" name="positionDescriptionEdu" size="56" value="">
                        </dd>
                    </dl>
                </c:when>
            </c:choose>
        </c:forEach>
        <hr>
    </form>
    <button form="addForm" type="submit">Сохранить</button>
    <button form="addForm" onclick="window.history.back()">Отменить</button>
</section>
<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>

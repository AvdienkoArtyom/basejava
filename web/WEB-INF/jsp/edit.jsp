<%@ page import="ru.mail.avdienkoartyom.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.mail.avdienkoartyom.model.ContactType" %>
<%@ page import="ru.mail.avdienkoartyom.model.SectionType" %>
<%@ page import="ru.mail.avdienkoartyom.model.OrganizationSection" %>
<%@ page import="ru.mail.avdienkoartyom.model.Organization" %>
<%@ page import="ru.mail.avdienkoartyom.model.Position" %>
<%@ page import="java.util.ArrayList" %>
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
            <dd>
            <dt><h3>${type.title}</h3></dt>
            <c:if test="${type.name()=='PERSONAL'||type.name()=='OBJECTIVE'}">
                <br>
                <input type="text" name="${type.name()}" size="56" value="${resume.section.get(type)}"><br>
            </c:if>
            <c:if test="${type.name()=='ACHIEVEMENT'||type.name()=='QUALIFICATIONS'}">
                <br>
                <textarea type="text" rows="10" cols="45" name="${type.name()}">${resume.section.get(type)}</textarea>
            </c:if>
            </dd>
        </dl>
        <dl>
            <dd>
                <c:choose>
                <c:when test="${type.name()=='EXPERIENCE'}">
                        <%
                            OrganizationSection organizationSectionExp = (OrganizationSection) resume.getSection().get(SectionType.EXPERIENCE);
                            if (organizationSectionExp != null) {
                                int sizeExp = organizationSectionExp.getOrganizationList().size();
                                int count = 0;
                                for (Organization organization : organizationSectionExp.getOrganizationList()) {
                                    int countTwo = 0;
                        %>
                <input type="hidden" name="countExp" value="<%=sizeExp%>">

            <dt>Название организации:</dt>
            <input type="text" name="<%=count%>titleExp" size="56" value="<%=organization.getTitle()%>"><br>
            <dt>сайт:</dt>
            <input type="text" name="<%=count%>urlExp" size="56" value="<%=organization.getUrl()%>"><br>

            <%
                int sizeOrg = organization.getPositionList().size();

                for (Position position : organization.getPositionList()) {
            %>
            <input type="hidden" name="<%=count%>countOrgExp" value="<%=sizeOrg%>">
            <dt>начало раборы:</dt>
            <input type="text" name="<%=count%>positionStartExp<%=countTwo%>" size="56"
                   value="<%=position.getDateStart()%>"><br>
            <dt>окончание работы:</dt>
            <input type="text" name="<%=count%>positionFinishExp<%=countTwo%>" size="56"
                   value="<%=position.getDateFinish()%>"><br>
            <dt>статус:</dt>
            <input type="text" name="<%=count%>positionStatusExp<%=countTwo%>" size="56"
                   value="<%=position.getStatus()%>"><br>
            <dt>описание:</dt>
            <input type="text" name="<%=count%>positionDescriptionExp<%=countTwo%>" size="56"
                   value="<%=position.getDescription()%>"><br>
            <%
                            count = count++;
                        }
                        count++;
                    }
                }
            %>
            <br>
            <dt>Добавить организацию:</dt>
            <br>
            <dt>Название организации:</dt>
            <input type="text" name="titleExp" size="56" value=""><br>
            <dt>сайт:</dt>
            <input type="text" name="urlExp" size="56" value=""><br>

            <dt>начало раборы:</dt>
            <input type="text" name="positionStartExp" size="56" placeholder="год-месяц-день" value=""><br>
            <dt>окончание работы:</dt>
            <input type="text" name="positionFinishExp" size="56" placeholder="год-месяц-день" value=""><br>
            <dt>статус:</dt>
            <input type="text" name="positionStatusExp" size="56" value=""><br>
            <dt>описание:</dt>
            <input type="text" name="positionDescriptionExp" size="56" value=""><br>
            </c:when>

            <c:when test="${type.name()=='EDUCATION'}">
                <%
                    OrganizationSection organizationSectionEdu = (OrganizationSection) resume.getSection().get(SectionType.EDUCATION);
                    if (organizationSectionEdu != null) {
                        int sizeEdu = organizationSectionEdu.getOrganizationList().size();
                        int count = 0;
                        for (Organization organization : organizationSectionEdu.getOrganizationList()) {
                            int countTwo = 0;
                %>
                <input type="hidden" name="countEdu" value="<%=sizeEdu%>">
                <dt>Название организации:</dt>
                <input type="text" name="<%=count%>titleEdu" size="56" value="<%=organization.getTitle()%>"><br>
                <dt>сайт:</dt>
                <input type="text" name="<%=count%>urlEdu" size="56" value="<%=organization.getUrl()%>"><br>
                <%
                    int sizeOrg = organization.getPositionList().size();
                    for (Position position : organization.getPositionList()) {
                %>
                <input type="hidden" name="<%=count%>countOrgEdu" value="<%=sizeOrg%>">
                <dt>начало раборы:</dt>
                <input type="text" name="<%=count%>positionStartEdu<%=countTwo%>" size="56"
                       value="<%=position.getDateStart()%>"><br>
                <dt>окончание работы:</dt>
                <input type="text" name="<%=count%>positionFinishEdu<%=countTwo%>" size="56"
                       value="<%=position.getDateFinish()%>"><br>
                <dt>описание:</dt>
                <input type="text" name="<%=count%>positionDescriptionEdu<%=countTwo%>" size="56"
                       value="<%=position.getDescription()%>"><br>
                <%
                                countTwo++;
                            }
                            count++;
                        }
                    }
                %>
                <br>
                <dt style="align: center">Добавить организацию:</dt>
                <br>
                <dt>Название организации:</dt>
                <input type="text" name="titleEdu" size="56" value=""><br>
                <dt>сайт:</dt>
                <input type="text" name="urlEdu" size="56" value=""><br>

                <dt>начало раборы:</dt>
                <input type="text" name="positionStartEdu" placeholder="год-месяц-день" size="56" value=""><br>
                <dt>окончание работы:</dt>
                <input type="text" name="positionFinishEdu" placeholder="год-месяц-день" size="56" value=""><br>
                <dt>описание:</dt>
                <input type="text" name="positionDescriptionEdu" size="56" value=""><br>
            </c:when>
            </c:choose>
            <br>
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

package ru.mail.avdienkoartyom;


import ru.mail.avdienkoartyom.model.*;

import java.util.ArrayList;
import java.util.List;

public class TestDataResume {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        resume.getContact().add("Тел.: +7(921) 855-0482");
        resume.getContact().add("Skype: grigory.kislin");
        resume.getContact().add("Почта: gkislin@yandex.ru");

        List<AbstractSection> listPersonal = new ArrayList<>();
        listPersonal.add(new Personal("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.getMap().put(SectionType.PERSONAL, listPersonal);

        List<AbstractSection> listObjective = new ArrayList<>();
        listObjective.add(new Objective("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.getMap().put(SectionType.OBJECTIVE, listObjective);

        List<AbstractSection> listAchievement = new ArrayList<>();
        listAchievement.add(new Achievement("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников."));
        listAchievement.add(new Achievement("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk."));
        listAchievement.add(new Achievement("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера."));
        listAchievement.add(new Achievement("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга."));
        listAchievement.add(new Achievement("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django)."));
        listAchievement.add(new Achievement("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));
        resume.getMap().put(SectionType.ACHIEVEMENT, listAchievement);

        List<AbstractSection> listQualification = new ArrayList<>();
        listQualification.add(new Qualifications("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2"));
        listQualification.add(new Qualifications("Version control: Subversion, Git, Mercury, ClearCase, Perforce"));
        listQualification.add(new Qualifications("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB"));
        listQualification.add(new Qualifications("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,"));
        listQualification.add(new Qualifications("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)."));
        listQualification.add(new Qualifications("Python: Django."));
        listQualification.add(new Qualifications("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js"));
        listQualification.add(new Qualifications("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka"));
        listQualification.add(new Qualifications("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT."));
        listQualification.add(new Qualifications("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,"));
        listQualification.add(new Qualifications("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer."));
        listQualification.add(new Qualifications("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования."));
        listQualification.add(new Qualifications("Родной русский, английский \"upper intermediate\"."));
        resume.getMap().put(SectionType.QUALIFICATIONS, listQualification);

        List<AbstractSection> listExperience = new ArrayList<>();
        listExperience.add(new Experience(new Organization("Alcatel"), "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).", "09/1997 - 01/2005"));
        listExperience.add(new Experience(new Organization("Siemens AG"), "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).", "01/2005 - 02/2007"));
        listExperience.add(new Experience(new Organization("Enkata"), "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).", "03/2007 - 06/2008"));
        listExperience.add(new Experience(new Organization("Yota"), "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)", "06/2008 - 12/2010"));
        listExperience.add(new Experience(new Organization("Luxoft (Deutsche Bank)"), "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.", "12/2010 - 04/2012"));

        resume.getMap().put(SectionType.EXPERIENCE, listExperience);

        List<AbstractSection> listEducation = new ArrayList<>();
        listEducation.add(new Education(new Organization("Заочная физико-техническая школа при МФТИ"), "Закончил с отличием", "09/1984 - 06/1987"));
        listEducation.add(new Education(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики"), "Инженер (программист Fortran, C)", "09/1987 - 07/1993"));
        listEducation.add(new Education(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики"), "Аспирантура (программист С, С++)", "09/1993 - 07/1996"));
        listEducation.add(new Education(new Organization("Alcatel"), "6 месяцев обучения цифровым телефонным сетям (Москва)", "09/1997 - 03/1998"));
        listEducation.add(new Education(new Organization("Siemens AG"), "3 месяца обучения мобильным IN сетям (Берлин)", "01/2005 - 04/2005"));
        listEducation.add(new Education(new Organization("Luxoft"), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", "03/2011 - 04/2011"));
        listEducation.add(new Education(new Organization("Coursera"), "\"Functional Programming Principles in Scala\" by Martin Odersky", "03/2013 - 05/2013"));
        resume.getMap().put(SectionType.EDUCATION, listEducation);

        System.out.println(resume.toString());
    }
}

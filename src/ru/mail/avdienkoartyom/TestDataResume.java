package ru.mail.avdienkoartyom;

import ru.mail.avdienkoartyom.model.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class TestDataResume {

    public static void main(String[] args) {

        Resume resume = new Resume("Григорий Кислин");

        resume.getContactStringMap().put(Contact.TELEPHONE, "+7(921) 855-0482");
        resume.getContactStringMap().put(Contact.EMAIL, "gkislin@yandex.ru");
        resume.getContactStringMap().put(Contact.SKYPE, "grigory.kislin");
        resume.getContactStringMap().put(Contact.PROFILE_LINKEDIN, "LinkedIn");
        resume.getContactStringMap().put(Contact.PROFILE_GITHUB, "GitHub");
        resume.getContactStringMap().put(Contact.PROFILE_STACKOVERFLOW, "Stackoverflow");
        resume.getContactStringMap().put(Contact.HOMEPAGE, "Домашняя страница");

        resume.getSectionTypeAbstractSectionMap().put(SectionType.PERSONAL, new Personal("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        List<AbstractSection> listObjective = new ArrayList<>();
        listObjective.add(new Objective("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.getSectionTypeAbstractSectionMap().put(SectionType.OBJECTIVE, new Objective("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        resume.getSectionTypeAbstractSectionMap().put(SectionType.ACHIEVEMENT,
                new Achievement("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"," +
                        " \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие " +
                        "(JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников." +
                        "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                        "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk." +
                        "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM." +
                        " Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением " +
                        "на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных " +
                        "ERP модулей, интеграция CIFS/SMB java сервера." +
                        "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC," +
                        " GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга." +
                        "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных " +
                        "сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов " +
                        "и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента " +
                        "для администрирования и мониторинга системы по JMX (Jython/ Django)." +
                        "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat," +
                        " Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));


        resume.getSectionTypeAbstractSectionMap().put(SectionType.QUALIFICATIONS,
                new Qualifications("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 " +
                        "Version control: Subversion, Git, Mercury, ClearCase, Perforce " +
                        "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB " +
                        "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, C/C++, Unix shell scripts, " +
                        "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements). " +
                        "Python: Django. " +
                        "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js " +
                        "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka " +
                        "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT. " +
                        "Инструменты: Maven + plugin development, Gradle, настройка Ngnix, " +
                        "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer. " +
                        "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования. " +
                        "Родной русский, английский \"upper intermediate\"."));


        Experience experience = new Experience();
        experience.getOrganizationList().add(new Organization("Alcatel", LocalDate.of(1997, Month.SEPTEMBER, 1), LocalDate.of(2005, Month.JANUARY, 1), "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."));
        experience.getOrganizationList().add(new Organization("Siemens AG", LocalDate.of(2005, Month.JANUARY, 1), LocalDate.of(2007, Month.FEBRUARY, 1), "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."));
        experience.getOrganizationList().add(new Organization("Enkata", LocalDate.of(2007, Month.MARCH, 1), LocalDate.of(2008, Month.JUNE, 1), "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."));
        experience.getOrganizationList().add(new Organization("Yota", LocalDate.of(2008, Month.JUNE, 1), LocalDate.of(2010, Month.DECEMBER, 1), "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"));
        experience.getOrganizationList().add(new Organization("Luxoft (Deutsche Bank)", LocalDate.of(2010, Month.DECEMBER, 1), LocalDate.of(2012, Month.APRIL, 1), "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."));
        experience.getOrganizationList().add(new Organization("RIT Center", LocalDate.of(2012, Month.APRIL, 1), LocalDate.of(2014, Month.OCTOBER, 1), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"));
        experience.getOrganizationList().add(new Organization("Wrike", LocalDate.of(2014, Month.OCTOBER, 1), LocalDate.of(2016, Month.JANUARY, 1), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        experience.getOrganizationList().add(new Organization("Java Online Projects", LocalDate.of(2013, Month.OCTOBER, 1), LocalDate.now(), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок."));

        resume.getSectionTypeAbstractSectionMap().put(SectionType.EXPERIENCE, experience);

        Education education = new Education();
        education.getOrganizationList().add(new Organization("Заочная физико-техническая школа при МФТИ", LocalDate.of(1984, Month.SEPTEMBER, 1), LocalDate.of(1987, Month.JUNE, 1), "Закончил с отличием"));
        education.getOrganizationList().add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", LocalDate.of(1987, Month.SEPTEMBER, 1), LocalDate.of(1993, Month.JULY, 1), "Инженер (программист Fortran, C)"));
        education.getOrganizationList().add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", LocalDate.of(1993, Month.SEPTEMBER, 1), LocalDate.of(1996, Month.JULY, 1), "Аспирантура (программист С, С++)"));
        education.getOrganizationList().add(new Organization("Alcatel", LocalDate.of(1997, Month.SEPTEMBER, 1), LocalDate.of(1998, Month.MARCH, 1), "6 месяцев обучения цифровым телефонным сетям (Москва)"));
        education.getOrganizationList().add(new Organization("Siemens AG", LocalDate.of(2005, Month.JANUARY, 1), LocalDate.of(2005, Month.APRIL, 1), "3 месяца обучения мобильным IN сетям (Берлин)"));
        education.getOrganizationList().add(new Organization("Luxoft", LocalDate.of(2011, Month.MARCH, 1), LocalDate.of(2011, Month.APRIL, 1), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\""));
        education.getOrganizationList().add(new Organization("Coursera", LocalDate.of(2013, Month.MARCH, 1), LocalDate.of(2011, Month.MAY, 1), "\"Functional Programming Principles in Scala\" by Martin Odersky"));

        resume.getSectionTypeAbstractSectionMap().put(SectionType.EDUCATION, education);

        System.out.println(resume.toString());
    }
}

package ru.mail.avdienkoartyom;

import ru.mail.avdienkoartyom.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class TestDataResume {

    public static void main(String[] args) {

        Resume resume1 = new Resume("Григорий Кислин");

        resume1.getContact().put(ContactType.TELEPHONE, "+7(921) 855-0482");
        resume1.getContact().put(ContactType.EMAIL, "gkislin@yandex.ru");
        resume1.getContact().put(ContactType.SKYPE, "grigory.kislin");
        resume1.getContact().put(ContactType.PROFILE_LINKEDIN, "LinkedIn");
        resume1.getContact().put(ContactType.PROFILE_GITHUB, "GitHub");
        resume1.getContact().put(ContactType.PROFILE_STACKOVERFLOW, "Stackoverflow");
        resume1.getContact().put(ContactType.HOMEPAGE, "Домашняя страница");

        resume1.getSection().put(SectionType.PERSONAL, new SympleTextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume1.getSection().put(SectionType.OBJECTIVE, new SympleTextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        ArrayList<String> achiList = new ArrayList<>();

        achiList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achiList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achiList.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM.Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achiList.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achiList.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achiList.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        resume1.getSection().put(SectionType.ACHIEVEMENT, new ListSection(achiList));

        ArrayList<String> experList = new ArrayList<>();

        experList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2.");
        experList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce.");
        experList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB.");
        experList.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, C/C++, Unix shell scripts.");
        experList.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        experList.add("Python: Django.");
        experList.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js.");
        experList.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka.");
        experList.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        experList.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix.");
        experList.add("Администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        experList.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования.");
        experList.add("Родной русский, английский \"upper intermediate\".");

        resume1.getSection().put(SectionType.QUALIFICATIONS, new ListSection(experList));

        List<Organization> orgExpList = new ArrayList<>();

        orgExpList.add(new Organization("Java Online Projects", Arrays.asList(new Period(LocalDate.of(2013, Month.OCTOBER, 1), LocalDate.now(), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок."))));
        orgExpList.add(new Organization("Wrike", Arrays.asList(new Period(LocalDate.of(2014, Month.OCTOBER, 1), LocalDate.of(2016, Month.JANUARY, 1), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."))));
        orgExpList.add(new Organization("RIT Center", Arrays.asList(new Period(LocalDate.of(2012, Month.APRIL, 1), LocalDate.of(2014, Month.OCTOBER, 1), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"))));
        orgExpList.add(new Organization("Luxoft (Deutsche Bank)", Arrays.asList(new Period(LocalDate.of(2010, Month.DECEMBER, 1), LocalDate.of(2012, Month.APRIL, 1), "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."))));
        orgExpList.add(new Organization("Yota", Arrays.asList(new Period(LocalDate.of(2008, Month.JUNE, 1), LocalDate.of(2010, Month.DECEMBER, 1), "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"))));
        orgExpList.add(new Organization("Enkata", Arrays.asList(new Period(LocalDate.of(2007, Month.MARCH, 1), LocalDate.of(2008, Month.JUNE, 1), "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."))));
        orgExpList.add(new Organization("Siemens AG", Arrays.asList(new Period(LocalDate.of(2005, Month.JANUARY, 1), LocalDate.of(2007, Month.FEBRUARY, 1), "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."))));
        orgExpList.add(new Organization("Alcatel", Arrays.asList(new Period(LocalDate.of(1997, Month.SEPTEMBER, 1), LocalDate.of(2005, Month.JANUARY, 1), "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."))));

        resume1.getSection().put(SectionType.EXPERIENCE, new OrganizationSection(orgExpList));

        List<Organization> orgEduList = new ArrayList<>();
        orgEduList.add(new Organization("Coursera", Arrays.asList(new Period(LocalDate.of(2013, Month.MARCH, 1), LocalDate.of(2011, Month.MAY, 1), "\"Functional Programming Principles in Scala\" by Martin Odersky"))));
        orgEduList.add(new Organization("Luxoft", Arrays.asList(new Period(LocalDate.of(2011, Month.MARCH, 1), LocalDate.of(2011, Month.APRIL, 1), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\""))));
        orgEduList.add(new Organization("Siemens AG", Arrays.asList(new Period(LocalDate.of(2005, Month.JANUARY, 1), LocalDate.of(2005, Month.APRIL, 1), "3 месяца обучения мобильным IN сетям (Берлин)"))));
        orgEduList.add(new Organization("Alcatel", Arrays.asList(new Period(LocalDate.of(1997, Month.SEPTEMBER, 1), LocalDate.of(1998, Month.MARCH, 1), "6 месяцев обучения цифровым телефонным сетям (Москва)"))));
        orgEduList.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", Arrays.asList(new Period(LocalDate.of(1987, Month.SEPTEMBER, 1), LocalDate.of(1993, Month.JULY, 1), "Инженер (программист Fortran, C)"), new Period(LocalDate.of(1993, Month.SEPTEMBER, 1), LocalDate.of(1996, Month.JULY, 1), "Аспирантура (программист С, С++)"))));
        orgEduList.add(new Organization("Заочная физико-техническая школа при МФТИ", Arrays.asList(new Period(LocalDate.of(1984, Month.SEPTEMBER, 1), LocalDate.of(1987, Month.JUNE, 1), "Закончил с отличием"))));

        resume1.getSection().put(SectionType.EDUCATION, new OrganizationSection(orgEduList));

        System.out.println(resume1.toString());

        Resume resume2 = new Resume("Авдиенко Артем");

        resume2.getContact().put(ContactType.TELEPHONE, "+7(000) 000-0000");
        resume2.getContact().put(ContactType.EMAIL, "email@yandex.ru");
        resume2.getContact().put(ContactType.SKYPE, "skype_account");
        resume2.getContact().put(ContactType.PROFILE_GITHUB, "GitHub");

        resume2.getSection().put(SectionType.PERSONAL, new SympleTextSection("Аналитический склад ума, креативность, инициативность"));
        resume2.getSection().put(SectionType.OBJECTIVE, new SympleTextSection("Учусь програмированию на Java."));

        ArrayList<String> achiList2 = new ArrayList<>();

        achiList2.add("С 2019 года начал изучать курс BaseJava");

        resume2.getSection().put(SectionType.ACHIEVEMENT, new ListSection(achiList2));

        ArrayList<String> experList2 = new ArrayList<>();

        experList2.add("Java core");

        resume2.getSection().put(SectionType.QUALIFICATIONS, new ListSection(experList2));

        List<Organization> orgExpList2 = new ArrayList<>();

        orgExpList2.add(new Organization("No name Organization.", Arrays.asList(new Period(LocalDate.of(2008, Month.OCTOBER, 1), LocalDate.now(), "Разнорабочий", "Продажи, переносы движимого имущества."))));

        resume2.getSection().put(SectionType.EXPERIENCE, new OrganizationSection(orgExpList2));

        List<Organization> orgEduList2 = new ArrayList<>();
        orgEduList2.add(new Organization("ВУМО РФ", Arrays.asList(new Period(LocalDate.of(2004, Month.AUGUST, 1), LocalDate.of(2008, Month.MAY, 1), "Менеджер социально-культурной деятельности.(Неоконченое высшее)"))));
        orgEduList2.add(new Organization("СОШ № 34 Южно - Сахалинска", Arrays.asList(new Period(LocalDate.of(1993, Month.SEPTEMBER, 1), LocalDate.of(2003, Month.MAY, 1), "Обучение по системе Занкова(любой предпологаемый ответ ученика являеться правильным.)"))));

        resume2.getSection().put(SectionType.EDUCATION, new OrganizationSection(orgEduList2));

        System.out.println(resume2.toString());

    }
}

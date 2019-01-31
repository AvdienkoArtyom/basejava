package ru.mail.avdienkoartyom;

import ru.mail.avdienkoartyom.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Resume resume = new Resume();

        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        field.set(resume, "Newuuid");
        Class myClass = Resume.class;
        Method method = myClass.getDeclaredMethod("toString");
        String result = (String) method.invoke(resume);
        System.out.println(result); //проверка
    }
}

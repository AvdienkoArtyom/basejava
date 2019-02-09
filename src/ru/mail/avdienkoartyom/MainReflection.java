package ru.mail.avdienkoartyom;

import ru.mail.avdienkoartyom.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Resume resume = new Resume();
        Class myClass = Resume.class;
        Method method = myClass.getDeclaredMethod("toString");
        System.out.println(method.invoke(resume));
    }
}

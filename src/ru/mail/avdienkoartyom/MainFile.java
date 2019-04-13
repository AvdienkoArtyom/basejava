package ru.mail.avdienkoartyom;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {

        File file = new File("C:\\Users\\Artem\\basejava\\src");
        traversingFileRecursion(file);
    }

    public static void traversingFileRecursion(File file) {
        if (!file.exists()) {
            System.out.println("Folder or file is not exists!");
        }
        if (file.isDirectory()) {
            System.out.println("Folder: " + file.getName());
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isDirectory()) {
                    traversingFileRecursion(f);
                } else {
                    System.out.println("File: " + file.getName());
                }
            }
        }
    }
}


package ru.mail.avdienkoartyom;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainFile {

    public static void main(String[] args) {

        File file = new File("C:\\Users\\Artem\\basejava\\src");
        traversingFileRecursion(file, "");
    }

    public static void traversingFileRecursion(File file, String tab) {
        if (!file.exists()) {
            System.out.println("Folder or file is not exists!");
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            List<String> fileList = new ArrayList<>();
            for (File f : files) {
                if (f.isDirectory()) {
                    System.out.println(tab + "Folder: " + f.getName());
                    traversingFileRecursion(f, tab + "    ");
                } else {
                    fileList.add(tab + "file: " + f.getName());
                }
            }
            for (String s : fileList) {
                System.out.println(s);
            }
        }
    }
}


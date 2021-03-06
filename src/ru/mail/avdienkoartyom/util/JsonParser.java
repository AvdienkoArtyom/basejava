package ru.mail.avdienkoartyom.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.mail.avdienkoartyom.model.AbstractSection;
import ru.mail.avdienkoartyom.model.SectionType;

import java.io.Reader;
import java.io.Writer;

public class JsonParser {
    private static Gson GSON = new GsonBuilder()
            .registerTypeAdapter(AbstractSection.class, new JsonSectionAdapter())
            .create();

    public static <T> T read(Reader reader, Class<T> clazz) {
        return GSON.fromJson(reader, clazz);
    }

    public static <T> void write(T object, Writer writer) {
        GSON.toJson(object, writer);
    }

    public static <T> T read(String reader, Class<T> clazz) {
        return GSON.fromJson(reader, clazz);
    }

    public static <T> String write(T object, Class<T> clazz) {
        return GSON.toJson(object, clazz);
    }

    public static <T> String write(T object) {
        return GSON.toJson(object);
    }
}

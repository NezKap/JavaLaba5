package org.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Field;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

/**
 * Класс, осуществляющий поиск полей с аннотацией
 * AutoInjectable и создание экземпляров классов из
 * файла config.properties, используя рефлексию
 * @param <T> тип объекта
 * @author Егор
 * @version 1.0
 */

public class Injector<T> {

    /**
     * Поиск полей с определённой аннотацией
     * и создание экземпляров классов
     * из файла config.properties
     * @param object объект
     * @return объект
     */

    public T inject(T object) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(input);
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        Class<?> objectClass = object.getClass();
        Field[] fields = objectClass.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(AutoInjectable.class)) {
                    String implementationClassName = properties.getProperty(field.getType().getName());
                    try {
                        Class<?> implementationClass = Class.forName(implementationClassName);
                        Object implementationInstance = implementationClass.getDeclaredConstructor().newInstance();
                        field.setAccessible(true);
                        field.set(object, implementationInstance);
                    }
                    catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
        }
        return object;
    }
}

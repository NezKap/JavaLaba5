package org.example;

import java.lang.annotation.*;

/**
 * Аннотация для полей, которые нужно инициализировать
 * @author Егор
 * @version 1.0
 */

@Retention(RetentionPolicy.RUNTIME)

@Target(ElementType.FIELD)


public @interface AutoInjectable {

}

package org.example;

/**
 * Класс с объявленными полями
 * @author Егор
 * @version 1.0
 */
class SomeBean {
    @AutoInjectable
    private SomeInterface field1;
    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Метод, вызывающий методы у полей
     */

    public void foo() {
        field1.doSomething();
        field2.doSomething();
    }
}

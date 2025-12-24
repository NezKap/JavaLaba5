package org.example;

public class Main {
    public static void main(String[] args) {
        Injector<SomeBean> object = new Injector<>();
        SomeBean firstSomeBean = object.inject(new SomeBean());
        Class<?> firstObjectClass = firstSomeBean.getClass();
        System.out.println(firstObjectClass);
        firstSomeBean.foo();
        SomeBean secondSomeBean = (new Injector<SomeBean>()).inject(new SomeBean());
        Class<?> secondObjectClass = firstSomeBean.getClass();
        System.out.println(secondObjectClass);
        secondSomeBean.foo();
    }
}
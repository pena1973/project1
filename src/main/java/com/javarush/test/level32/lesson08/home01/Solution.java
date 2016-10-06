package com.javarush.test.level32.lesson08.home01;

import java.lang.reflect.Proxy;

/* Создание прокси-объекта
1) В отдельном файле создать публичный класс CustomInvocationHandler, который будет хэндлером при создании прокси-объекта.
2) CustomInvocationHandler должен иметь один публичный конструктор с одним агументом типа SomeInterfaceWithMethods.
3) Перед вызовом любого метода у оригинального объекта должна выводиться фраза [methodName in].
4) После вызова любого метода у оригинального объекта должна выводиться фраза [methodName out].
5) Реализовать логику метода getProxy, который должен создавать прокси для интерфейса SomeInterfaceWithMethods.
См. пример вывода в методе main. Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) {
        SomeInterfaceWithMethods obj = getProxy();
        obj.stringMethodWithoutArgs();
        obj.voidMethodWithIntArg(1);

        /* expected output
        stringMethodWithoutArgs in
        inside stringMethodWithoutArgs
        stringMethodWithoutArgs out
        voidMethodWithIntArg in
        inside voidMethodWithIntArg
        inside voidMethodWithoutArgs
        voidMethodWithIntArg out
        */
    }

    public static SomeInterfaceWithMethods getProxy() {
        SomeInterfaceWithMethods methods = new SomeInterfaceWithMethodsImpl();
        ClassLoader classLoader = methods.getClass().getClassLoader();
        Class<?>[] interfaces = methods.getClass().getInterfaces();
        CustomInvocationHandler invocationHandler = new CustomInvocationHandler(methods);
        SomeInterfaceWithMethods someInterfaceWithMethods = (SomeInterfaceWithMethods) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        return someInterfaceWithMethods;

    }
}

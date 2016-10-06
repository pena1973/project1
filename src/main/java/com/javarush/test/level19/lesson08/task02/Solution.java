package com.javarush.test.level19.lesson08.task02;

/* Ридер обертка 2
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна заменять все подстроки "te" на "??"
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток
*/

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException
    {
        //Запоминаем настоящий PrintStream в специальную переменную
        PrintStream saveOld = System.out;

        // Создаем динамический массив
        OutputStream outputStream = new ByteArrayOutputStream();
        //Создаем адаптер к классу PrintStream
        PrintStream printStream = new PrintStream(outputStream);
        //Устанавливаем его как текущий System.out;
        System.setOut(printStream);

        //  дальше - печатаем  строку которая вместо вывода попадает в массив
        testString.printSomething();
        // Преобразуем полученные данные в строку
        String result = outputStream.toString();
        result = result.replaceAll("te","??");
        // возвращаем как было настоящий PrintStream
        System.setOut(saveOld);

        // Выводим в консоль преобразованную строку
        System.out.println(result);

        outputStream.close();
        printStream.close();
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
    }
    }
}

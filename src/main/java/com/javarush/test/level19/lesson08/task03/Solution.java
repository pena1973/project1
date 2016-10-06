package com.javarush.test.level19.lesson08.task03;

/* Выводим только цифры
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить только цифры
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Пример вывода:
12345678
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

        // возвращаем как было настоящий PrintStream
        System.setOut(saveOld);
        // Преобразуем полученные данные в строку
        String result = outputStream.toString();
        result = result.replaceAll("\\D","");
        result = result.replaceAll("\\p{Punct}","");
        result = result.replaceAll(" ","");
        // Выводим в консоль преобразованную строку
        System.out.println(result);

        outputStream.close();
        printStream.close();
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's 1 a 23 text 4 f5-6or7 tes8ting");
        }
    }
}

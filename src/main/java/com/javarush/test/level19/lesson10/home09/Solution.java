package com.javarush.test.level19.lesson10.home09;

/* Контекстная реклама
В методе main подмените объект System.out написанной вами реадер-оберткой
Ваша реадер-обертка должна выводить на консоль контекстную рекламу после каждого второго println-а
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Рекламный текст: "JavaRush - курсы Java онлайн"

Пример вывода:
first
second
JavaRush - курсы Java онлайн
third
fourth
JavaRush - курсы Java онлайн
fifth
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
        String[] arr = result.split("\\n");
        int count = 0;
        for (int i = 0; i < arr.length; i++)
        {
            System.out.println(arr[i]);
            count++;
            if (count==2){
                count = 0;
                System.out.println("JavaRush - курсы Java онлайн");
            }
        }

        // Выводим в консоль преобразованную строку


        outputStream.close();
        printStream.close();
    }


    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}

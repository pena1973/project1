package com.javarush.test.level19.lesson08.task04;

/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
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
        result = result.replaceAll("\\n","").replaceAll("\\r","");
        String[] arr = result.split(" ");

        switch (arr[1]) {
            case "+":  result = result + (Integer.parseInt(arr[0]) + Integer.parseInt(arr[2]));
                break;
            case "-":  result = result + (Integer.parseInt(arr[0]) - Integer.parseInt(arr[2]));
                break;
            case "*":  result = result + (Integer.parseInt(arr[0]) * Integer.parseInt(arr[2]));
                break;}

        // Выводим в консоль преобразованную строку
        System.out.println(result);

        outputStream.close();
        printStream.close();
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}


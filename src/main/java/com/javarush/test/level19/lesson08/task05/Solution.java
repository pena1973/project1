package com.javarush.test.level19.lesson08.task05;

/* Дублируем текст
Считайте с консоли имя файла
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна дублировать вывод всего текста в файл, имя которого вы считали
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток
Закройте поток файла

Пример вывода на экран:
it's a text for testing

Пример тела файла:
it's a text for testing
*/

import java.io.*;

public class Solution
{
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = br.readLine();
        // String fileName1 = "C:\\Users\\Sid927\\Desktop\\111.txt";

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName1));

        //Запоминаем настоящий PrintStream в специальную переменную
        PrintStream saveOld = System.out;
        // Создаем динамический массив
        //OutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
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
        // Выводим в консоль преобразованную строку
        System.out.println(result);
        // Выводим в файл  строку
        writer.write(result);

        outputStream.close();
        printStream.close();
        writer.close();
        br.close();
    }

    public static class TestString
    {
        public void printSomething()
        {
            System.out.println("it's a text for testing");
        }
    }
}


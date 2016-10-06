package com.javarush.test.level19.lesson10.home06;

import java.io.*;
import java.util.*;


/* Замена чисел
1. В статическом блоке инициализировать словарь map парами [число-слово] от 0 до 12 включительно
Например, 0 - "ноль", 1 - "один", 2 - "два"
2. Считать с консоли имя файла
3. Заменить все числа на слова используя словарь map
4. Результат вывести на экран
5. Закрыть потоки

Пример данных:
Это стоит 1 бакс, а вот это - 12 .
Переменная имеет имя file1.
110 - это число.

Пример вывода:
Это стоит один бакс, а вот это - двенадцать .
Переменная имеет имя file1.
110 - это число.
*/

public class Solution
{
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static
    {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одинадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = br.readLine();
        //String fileName1 = "C:\\Users\\Sid927\\Desktop\\111.txt";
        br.close();

        BufferedReader reader = new BufferedReader(new FileReader(fileName1));

        String lineIn = "";
        String lineOut = "";
        Integer key=null;

        while ((lineIn = reader.readLine()) != null)
        {
            //  получили отдельные слова
            String[] arr = lineIn.split("\\s");

            // перебираем слова строки
             for (int y = 0; y < arr.length; y++)
                {
                    key = null;
                    // перебираем числа с конца чтобы их найти и поменять
                    for (int i = map.size(); i >= 0; i--)
                    {
                        // ищем число для замены с конца

                        if (arr[y].equals(String.valueOf(i))) key = i;
                    }
                    if (key != null){
                        arr[y] = map.get(key);}
                }

            lineOut = "";
            // собираем строку обратно
            for (int y = 0; y < arr.length; y++)
            {
                lineOut = lineOut + arr[y]+" ";
            }
            // рублю последний пробел
            System.out.println(lineOut.trim());
        }
        reader.close();
    }
}

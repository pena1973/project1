package com.javarush.test.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Самые редкие байты
Ввести с консоли имя файла
Найти байты, которые встречаются в файле меньше всего раз.
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        Map<Integer, Integer> bytes = new HashMap<Integer, Integer>();
        int key;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstFileName  = reader.readLine();
        //  String firstFileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
        FileInputStream file = new FileInputStream(firstFileName);
        Integer min = 2147483647;
        // считаем и найдем макс
        while (file.available() > 0)
        {
            key = file.read();
            Integer count = bytes.get(key);
            if (count == null)
            {
                count = 0;
            }

            count++;
            bytes.put(key, count);
            min = Math.min(min, count);
        }
        //перебираем и выводим все что равно максу
        String line = "";
        for (Map.Entry<Integer, Integer> str : bytes.entrySet())
        {
            if (min == str.getValue())
            {
                if (line.isEmpty())
                    line = line + str.getKey();
                else
                    line = line + " " + str.getKey();
            }
        }
        //System.out.printf("%s", line);
        System.out.println(line);
        file.close();
    }

}


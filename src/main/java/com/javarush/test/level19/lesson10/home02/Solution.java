package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        String fileName1 = args[0];
        //String fileName1 = "C:\\Users\\Sid927\\Desktop\\111.txt";

        BufferedReader reader = new BufferedReader(new FileReader(fileName1));
        String line = "";
        Map<String, Double> map = new HashMap<>();

        double max = 0;
        while ((line = reader.readLine()) != null)
        {
            String[] arr = line.split(" ");
            map.put(arr[0], (map.get(arr[0]) == null ? 0 : map.get(arr[0])) + Double.parseDouble(arr[1]));
            max = Math.max(max, map.get(arr[0]));
        }

        String line1 = "";

        for (Map.Entry<String, Double> str : map.entrySet())
        {
            if (str.getValue() == max)

                line1 = line1 + str.getKey() + " ";
        }

        line1 = line1.trim();


        System.out.println(line1);

        reader.close();

    }
}

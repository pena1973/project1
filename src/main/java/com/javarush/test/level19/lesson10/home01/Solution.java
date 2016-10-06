package com.javarush.test.level19.lesson10.home01;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени
Закрыть потоки

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        String fileName1 = args[0];
        //String fileName1 = "C:\\Users\\Sid927\\Desktop\\111.txt";

        BufferedReader reader = new BufferedReader(new FileReader(fileName1));
        String line = "";
        Map<String,Float> map = new HashMap<>();


        while ((line = reader.readLine()) != null)
        {
            String[] arr = line.split(" ");
            map.put(arr[0],(map.get(arr[0]) == null ?  0 : map.get(arr[0]))+Float.parseFloat(arr[1]));

        }
        // Сортировка
        String[] arr = new String[map.size()];
        int count = 0;
        for (Map.Entry<String,Float> str: map.entrySet())
        {
            arr[count]= str.getKey();
            count++;
        }

        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++)
        {
            System.out.println(arr[i]+" "+map.get(arr[i]));
        }
        reader.close();


    }
}

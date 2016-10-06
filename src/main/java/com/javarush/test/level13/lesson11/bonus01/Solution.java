package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        //C:\Users\Sid927\Desktop\java\vek001.txt
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile = reader.readLine();
      //  String nameFile = "C:\\Users\\Sid927\\Desktop\\java\\vek001.txt";
        BufferedReader file = new BufferedReader(new FileReader(nameFile));

        String s;

        ArrayList<Integer> arr = new ArrayList<Integer>();
        String str = file.readLine();
        while (str != null)
        {
            arr.add(Integer.parseInt(str));
            str = file.readLine();
        }


        for (int i = 0; i < arr.size(); )
        {
            if (((int) arr.get(i) % 2) != 0)
            {
                arr.remove(i);

            }
            else
            {i++;}
        }
        Object[] array = arr.toArray();

        Arrays.sort(array);

        for (int i = 0; i < array.length; i++)
        {
            System.out.println(array[i]);
        }
    }

}


package com.javarush.test.level15.lesson12.home01;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/* Разные методы для разных типов
1. Считать с консоли данные, пока не введено слово "exit".
2. Для каждого значения вызвать метод print. Если значение:
2.1. содержит точку '.', то вызвать метод print для Double;
2.2. больше нуля, но меньше 128, то вызвать метод print для short;
2.3. больше либо равно 128, то вызвать метод print для Integer;
2.4. иначе, вызвать метод print для String.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        //напиште тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        int num = 0;
        List<String> list = new ArrayList<String>();
        while (!str.equals("exit"))
        {
            str = reader.readLine();
            list.add(str);
        }

        for (int i = 0; i < list.size()-1; i++)
        {
            str = list.get(i);

            if (str.indexOf('.') != -1)
            {
                try
                {
                    print(Double.parseDouble(str));
                }
                catch (Exception e)
                {
                }
            } else
            { //  попытка перевести в число
                try
                {
                    num = Integer.parseInt((str));
                    if (num > 0 && num < 128) print((short) num);
                    else if (num >= 128) print((Integer) num);
                }

                catch (Exception e)
                {
                    print(str);
                }

            }
        }

    }

    public static void print(Double value)
    {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value)
    {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value)
    {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value)
    {
        System.out.println("Это тип Integer, значение " + value);
    }
}

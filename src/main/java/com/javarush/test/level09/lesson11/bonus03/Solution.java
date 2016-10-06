package com.javarush.test.level09.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/* Задача по алгоритмам
Задача: Пользователь вводит с клавиатуры список слов (и чисел). Слова вывести в возрастающем порядке, числа - в убывающем.
Пример ввода:
Вишня
1
Боб
3
Яблоко
2
0
Арбуз
Пример вывода:
Арбуз
3
Боб
2
Вишня
1
0
Яблоко
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<String>();
        while (true)
        {
            String s = reader.readLine();
            if (s.isEmpty()) break;
            list.add(s);
        }

        String[] array = list.toArray(new String[list.size()]);
        sort(array);

        for (String x : array)
        {
            System.out.println(x);
        }
    }

    public static void sort(String[] array)
    {
        // Напишите тут ваш код

        ArrayList<Integer> arrNum = new ArrayList<Integer>();
        ArrayList<String> arrStr = new ArrayList<String>();
        for (int i = 0; i < array.length; i++)
        {
            try
            {
                int str = Integer.parseInt(array[i]);
                arrNum.add(str);
            }
            catch (Exception e)
            {
                arrStr.add(array[i]);
            }
        }
        Object[] arrNum1 = arrNum.toArray();
        String[] arrStr1 = {}; // конвертируем ArrayList в массив
        arrStr1 = arrStr.toArray(new String[arrStr.size()]);

        Arrays.sort(arrNum1);
        Arrays.sort(arrStr1);

        int countNum = arrNum1.length-1;
        int countStr = 0;
        for (int i = 0; i < array.length; i++)
        {
            try
            {
                int str = Integer.parseInt(array[i]);

                array[i] = arrNum1[countNum].toString();
                countNum--;
            }
            catch (Exception e)
            {
                array[i] = arrStr1[countStr].toString();
                countStr++;

            }
        }

    }

    //Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThen(String a, String b)
    {
        return a.compareTo(b) > 0;
    }


    //строка - это на самом деле число?
    public static boolean isNumber(String s)
    {
        if (s.length() == 0) return false;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++)
        {
            char c = chars[i];
            if ((i != 0 && c == '-') //есть '-' внутри строки
                    || (!Character.isDigit(c) && c != '-') ) // не цифра и не начинается с '-'
            {
                return false;
            }
        }
        return true;
    }
}

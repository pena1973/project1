package com.javarush.test.level06.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


/* Задача по алгоритмам
Задача: Написать программу, которая вводит с клавиатуры 5 чисел и выводит их в возрастающем порядке.
Пример ввода:
3
2
15
6
17
Пример вывода:
2
3
6
15
17
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
        //Напишите тут ваш код
        ArrayList arr = new ArrayList();

        for (int n = 0 ; n < 5; n++)
        {
            String num = reader.readLine();
            arr.add(n, Integer.parseInt(num));
        }

        Object[] arr1 = arr.toArray();
        Arrays.sort(arr1);  // сортировка списка


        for (int n = 0 ; n < 5; n++)
        {
            System.out.println(arr1[n]);
        }
    }
}

package com.javarush.test.level07.lesson12.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* Задача по алгоритмам
Задача: Написать программу, которая вводит с клавиатуры 20 чисел и выводит их в убывающем порядке.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = new int[20];
        for (int i = 0; i < 20; i++)
        {
            array[i] = Integer.parseInt(reader.readLine());
        }

        sort(array);

        for (int x : array)
        {
            System.out.println(x);
        }
    }

    public static void sort(int[] arr)
    {

       Arrays.sort(arr);
       // А теперь попарно переставляю значения в массиве
       // Arrays.sort(array, Collections.reverseOrder());
        int length = arr.length;
        int m = length/2;

        for (int i = 0 ; i < m; i++)
        {
            int temp = arr[i];
            arr[i] = arr[length-1-i];
            arr[length-1-i] = temp;
          }
    }
}

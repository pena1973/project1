package com.javarush.test.level04.lesson06.task03;

/* Сортировка трех чисел
Ввести с клавиатуры три числа, и вывести их в порядке убывания.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import  java.util.Collections;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        String name = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String a1 = reader.readLine();
        String a2 = reader.readLine();
        String a3 = reader.readLine();

        int[] myArr = new int[3];
        myArr[0] = Integer.parseInt(a1);
        myArr[1] = Integer.parseInt(a2);
        myArr[2]= Integer.parseInt(a3);

        Arrays.sort(myArr);
        System.out.println(myArr[2]);
        System.out.println(myArr[1]);
        System.out.println(myArr[0]);

    }
}

package com.javarush.test.level07.lesson09.task02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/* 5 слов в обратном порядке
Введи с клавиатуры 5 слов в список строк. Выведи их в обратном порядке.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код

        ArrayList<String> arr = new ArrayList<String>();
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));

        for (int n = 0 ; n < 5; n++)
        {
            arr.add(0, reader.readLine());
        }

        for (int n = 0 ; n < 5; n++)
        {
            System.out.println(arr.get(n));
        }

    }
}

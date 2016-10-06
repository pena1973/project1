package com.javarush.test.level07.lesson06.task05;

/* Удали последнюю строку и вставь её в начало
1. Создай список строк.
2. Добавь в него 5 строчек с клавиатуры.
3. Удали последнюю строку и вставь её в начало. Повторить 13 раз.
4. Используя цикл выведи содержимое на экран, каждое значение с новой строки.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код

        ArrayList<String> arr = new ArrayList<String>();

        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));

        for (int n = 0 ; n < 5; n++)
        {
            arr.add(n, reader.readLine());
        }

        for (int n = 0 ; n < 13; n++)
        {
            arr.add(0, arr.get(arr.size() - 1));
            arr.remove(arr.size() - 1);
        }


        for (int n = 0 ; n < 5; n++)
            System.out.println(arr.get(n));

    }
}


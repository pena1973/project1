package com.javarush.test.level07.lesson06.task04;

/* 5 строчек в начало списка
1. Создай список строк.
2. Добавь в него 5 строчек с клавиатуры, но только добавлять не в конец списка, а в начало.
3. Используя цикл выведи содержимое на экран, каждое значение с новой строки.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

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
            System.out.println(arr.get(n));

    }
}

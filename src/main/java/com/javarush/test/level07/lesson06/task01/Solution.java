package com.javarush.test.level07.lesson06.task01;

/* 5 различных строчек в списке
1. Создай список строк.
2. Добавь в него 5 различных строчек.
3. Выведи его размер на экран.
4. Используя цикл выведи его содержимое на экран, каждое значение с новой строки.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код
        ArrayList arr = new ArrayList();

        for (int n = 0 ; n < 5; n++)
            arr.add(n, ""+ n + n);

        System.out.println(arr.size());

        for (int n = 0 ; n < 5; n++)
            System.out.println(arr.get(n));


    }
}

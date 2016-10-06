package com.javarush.test.level07.lesson09.task03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/* Слово «именно»
1. Создай список из слов «мама», «мыла», «раму».
2. После каждого слова вставь в список строку, содержащую слово «именно».
3. Используя цикл for вывести результат на экран, каждый элемент списка с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код
        ArrayList<String> arr = new ArrayList<String>();
        arr.add(0, "мама");
        arr.add(1, "мыла");
        arr.add(2, "раму");

        arr.add(1, "именно");
        arr.add(3, "именно");
        arr.add(5, "именно");

        for (int n = 0 ; n < 6; n++)
        {
            System.out.println(arr.get(n));
        }

    }
}

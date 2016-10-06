package com.javarush.test.level07.lesson06.task02;

/* 5 строчек: «101», «102», «103», «104», «105»
1. Создай список строк.
2. Добавь в него 5 строчек: «101», «102», «103», «104», «105».
3. Удали первую, среднюю и последнюю.
4. Используя цикл выведи на экран его содержимое, каждое значение с новой строки.
5. Выведи его размер. (После удаления одного элемента индексы остальных меняются.
Например, если удалить 0-й элемент, то тот, который был 1-м, станет 0-м. И т.д.)
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
        int num = 100;

        for (int n = 0 ; n < 5; n++)
        {
            num = num + 1;
            arr.add(n, "" + num);
        }

        arr.remove(0);
        arr.remove(1);
        arr.remove(2);

        for (int n = 0 ; n < arr.size(); n++)
           System.out.println(arr.get(n));

        System.out.println(arr.size());

    }
}

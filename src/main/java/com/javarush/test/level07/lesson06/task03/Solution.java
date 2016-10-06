package com.javarush.test.level07.lesson06.task03;

/* 5 строчек в обратном порядке
1. Создай список строк.
2. Считай с клавиатуры 5 строк и добавь в него.
3. Расположи их в обратном порядке.
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
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> arr = new ArrayList<String>();

        for (int n = 0 ; n < 5; n++)
            arr.add(n, reader.readLine());

        int ln = arr.size()/2;  // получаю длину проловины массива
        // меняю местами крайние позиции
        for (int n = 0 ; n < ln; n++)
        {
            String temp1 = arr.get(n);
            String temp2 = arr.get(arr.size()-1 - n);
            arr.set(arr.size()-1 - n, temp1);
            arr.set(n, temp2);
        }

        for (int n = 0 ; n < arr.size(); n++){
            System.out.println(arr.get(n));
        }

    }
}

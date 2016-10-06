package com.javarush.test.level07.lesson12.home01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Вывести числа в обратном порядке
Ввести с клавиатуры 10 чисел и заполнить ими список.
Вывести их в обратном порядке.
Использовать только цикл for.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Напишите тут ваш код
        ArrayList<Integer> arr = new ArrayList<Integer>();

        for (int n = 0 ; n < 10; n++)
        {
            String num = reader.readLine();
            arr.add(n, Integer.parseInt(num));
        }

        for (int n = 0 ; n < 10; n++)
            System.out.println(arr.get(arr.size()-1-n));

    }
}

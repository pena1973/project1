package com.javarush.test.level05.lesson12.home05;

/* Вводить с клавиатуры числа и считать их сумму
Вводить с клавиатуры числа и считать их сумму, пока пользователь не введёт слово «сумма». Вывести на экран полученную сумму.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        int summ = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String y1 = reader.readLine(); // год
        while (!y1.equals("сумма"))
        {
            int yy1 = Integer.parseInt(y1);
            summ = summ+yy1;
            y1 = reader.readLine();
        }
        System.out.print(summ);

    }
}

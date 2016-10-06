package com.javarush.test.level04.lesson16.home03;

/* Посчитать сумму чисел
Вводить с клавиатуры числа и считать их сумму.
Если пользователь ввел -1, вывести на экран сумму и завершить программу.
-1 должно учитываться в сумме.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args)   throws Exception
    {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String y1 = reader.readLine(); // год
        int yy1 = Integer.parseInt(y1);
        int summ = yy1;
        while (yy1>0)
        {

            y1 = reader.readLine();
            yy1 = Integer.parseInt(y1);
            summ = summ+yy1;
        }
        System.out.print(summ);

    }
}

package com.javarush.test.level04.lesson13.task02;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Рисуем прямоугольник
Ввести с клавиатуры два числа m и n.
Используя цикл for вывести на экран прямоугольник размером m на n из восьмёрок.
Пример: m=2, n=4
8888
8888
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String xx = reader.readLine();
        String yy = reader.readLine();

        int xxx = Integer.parseInt(xx);
        int yyy = Integer.parseInt(yy);

        for (int i=0; i < xxx; i++)

        {
            for (int ii=0; ii < yyy; ii++)
            {
                System.out.print("8");
            }
            System.out.println("");
        }



    }
}

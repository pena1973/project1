package com.javarush.test.level04.lesson16.home02;

/* Среднее такое среднее
Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    private static int numbMin;
    private static int numbMax;

    public static void main(String[] args)   throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String a1 = reader.readLine();
        String a2 = reader.readLine();
        String a3 = reader.readLine();

        int aa1 = Integer.parseInt(a1);
        int aa2 = Integer.parseInt(a2);
        int aa3 = Integer.parseInt(a3);
        numbMin = Math.min(Math.min(aa1, aa2),aa3);
        numbMax = Math.max(Math.max(aa1, aa2), aa3);
        aver(aa1);
        aver(aa2);
        aver(aa3);

    }
    private static void aver (int number){
     if (number!=numbMin && number!=numbMax)
        System.out.println(number);
    }

}

package com.javarush.test.level04.lesson06.task02;

/* Максимум четырех чисел
Ввести с клавиатуры четыре числа, и вывести максимальное из них.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        String name = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String a1 = reader.readLine();
        String a2 = reader.readLine();
        String a3 = reader.readLine();
        String a4 = reader.readLine();

        int aa1 = Integer.parseInt(a1);
        int aa2 = Integer.parseInt(a2);
        int aa3 = Integer.parseInt(a3);
        int aa4 = Integer.parseInt(a4);


        int max1 = Math.max(aa1, aa2);
        int max2 = Math.max(aa3,aa4);
        int max3 = Math.max(max1,max2);

        System.out.println(max3);
        //Напишите тут ваш код

    }
}

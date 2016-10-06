package com.javarush.test.level12.lesson04.task04;

/* Три метода возвращают минимальное из двух переданных в него чисел
Написать public static методы: int min(int, int), long min(long, long), double min(double, double).
Каждый метод должен возвращать минимальное из двух переданных в него чисел.
*/

public class Solution
{
    public static void main(String[] args)
    {
        System.out.println(min(1, 3));
        System.out.println(min(1L, 3L));
        System.out.println(min(1d, 3d));
    }

    //Напишите тут ваши методы
    public static int min(int a1, int a2){return Math.min(a1,a2);}
    public static long min(long a1, long a2){return Math.min(a1,a2);}
    public static double min(double a1, double a2){return Math.min(a1,a2);}
}

package com.javarush.test.level12.lesson04.task05;

/* Три метода возвращают максимальное из двух переданных в него чисел
Написать public static методы: int max(int, int), long max (long, long), double max (double, double).
Каждый метод должен возвращать максимальное из двух переданных в него чисел.
*/

public class Solution
{
    public static void main(String[] args)
    {
        System.out.println(max(1, 3));
        System.out.println(max(1L, 3L));
        System.out.println(max(1d, 3d));
    }

    //Напишите тут ваши методы
    public static int max(int a1, int a2){return Math.max(a1,a2);}
    public static long max(long a1, long a2){return Math.max(a1, a2);}
    public static double max(double a1, double a2){return Math.max(a1, a2);}
}

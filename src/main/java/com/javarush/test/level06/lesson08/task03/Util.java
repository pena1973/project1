package com.javarush.test.level06.lesson08.task03;

/* Класс Util
Реализовать статический метод double getDistance(x1, y1, x2, y2). Он должен вычислять расстояние между точками.
Используй метод double Math.sqrt(double a), который вычисляет квадратный корень переданного параметра
*/

import static java.lang.Math.*;

public class Util
{
    public static double getDistance(int x1, int y1, int x2, int y2)
    {
        //Напишите тут ваш код
        //d =√((x2 - x1)2 + (y2 - y1)2
        //Math.pow(число, степень);
        return sqrt(pow((x2 - x1),2) +pow((y2 - y1),2));

    }
}

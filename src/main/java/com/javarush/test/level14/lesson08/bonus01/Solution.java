package com.javarush.test.level14.lesson08.bonus01;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }

    private static void initExceptions()
    {   //it's first exception
        try
        {
            float i = 1 / 0;

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try {int[] arr = new int[-1];} catch (NegativeArraySizeException e){exceptions.add(e); }
        try {int[] arr = new int[1];int s = arr[2];}catch (ArrayIndexOutOfBoundsException e){exceptions.add(e); }
        try {FileReader file = new FileReader("");}catch (Exception e){exceptions.add(e); }
        try {int y = Integer.parseInt("g");}catch (Exception e){exceptions.add(e); }
        try {throw new ExceptionMy();} catch (Exception e){exceptions.add(e); }
        try {throw new ExceptionMy1();} catch (Exception e){exceptions.add(e); }
        try {throw new ExceptionMy2();} catch (Exception e){exceptions.add(e); }
        try {throw new ExceptionMy3();} catch (Exception e){exceptions.add(e); }
        try {throw new ExceptionMy4();} catch (Exception e){exceptions.add(e); }

    }

    public static class ExceptionMy extends Exception
    {
    }
    public static class ExceptionMy1 extends Exception
    {
    }
    public static class ExceptionMy2 extends Exception
    {
    }
    public static class ExceptionMy3 extends Exception
    {
    }
    public static class ExceptionMy4 extends Exception
    {
    }


}



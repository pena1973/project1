package com.javarush.test.level08.lesson11.home09;

import java.util.Date;

/* Работа с датой
1. Реализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передается в формате MAY 1 2013
Пример:
JANUARY 1 2000 = true
JANUARY 2 2020 = false
*/

public class Solution
{
    public static void main(String[] args)
    {
        System.out.println(isDateOdd("JANUARY 1 2000"));
    }

    public static boolean isDateOdd(String date)
    {
        Date dateFinish = new Date(date);
        Date dateStart = new Date(date);
        dateStart.setMonth(0);
        dateStart.setDate(1);
        dateStart.setHours(0);
        dateStart.setMinutes(0);
        dateStart.setSeconds(0);
        long T = dateFinish.getTime() -dateStart.getTime(); // получила время в МС
        int days = (int) T /(24*60*60*1000);
        if (days % 2 > 0)
            return false;
        else
            return true;

    }
}

package com.javarush.test.level05.lesson12.home04;

/* Вывести на экран сегодняшнюю дату
Вывести на экран текущую дату в аналогичном виде "21 02 2014".
*/

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Solution
{
    public static void main(String[] args)
    {
      Date date = new Date();
      SimpleDateFormat frm = new SimpleDateFormat("dd MM yyyy");
    //  GregorianCalendar data = new GregorianCalendar();


      System.out.println(""+frm.format(date));

    }
}

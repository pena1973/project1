package com.javarush.test.level04.lesson16.home04;

/* Меня зовут 'Вася'...
Ввести с клавиатуры строку name.
Ввести с клавиатуры дату рождения (три числа): y, m, d.
Вывести на экран текст:
«Меня зовут name
Я родился d.m.y»
Пример:
Меня зовут Вася
Я родился 15.2.1988
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       String name = reader.readLine();
        String y1 = reader.readLine(); // год
        String m1 = reader.readLine(); // месяц
        String d1 = reader.readLine(); // день

        int yy1 = Integer.parseInt(y1);
        int mm1 = Integer.parseInt(m1);
        int dd1 = Integer.parseInt(d1);
        SimpleDateFormat frm = new SimpleDateFormat("d.MM.yyyy");

        GregorianCalendar data = new GregorianCalendar();
        data.set(yy1,mm1-1,dd1,0,0,0);
        Date data1 = data.getTime();
        System.out.println("Меня зовут "+ name);
        //System.out.println("Я родился "+frm.format(data1));
        String dataToString = ""+dd1+"."+mm1+"."+yy1;
        System.out.println("Я родился "+dataToString);
    }
}

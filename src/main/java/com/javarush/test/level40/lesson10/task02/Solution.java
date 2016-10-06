package com.javarush.test.level40.lesson10.task02;
// http://itech-notes.blogspot.ru/2012/08/joda-time.html
/* Работа с Joda Time
Выполни задание, используя библиотеку Joda Time версии 2.9.1
Реализуй метод printDate(String date).
Он должен в качестве параметра принимать дату (в одном из 3х форматов)
и выводить ее в консоль в соответсвии с примером:

1) Для "21.4.2014 15:56:45" вывод должен быть:
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1
AM или PM: 1
Часы: 3
Часы дня: 15
Минуты: 56
Секунды: 45

2) Для "21.4.2014":
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1

3) Для "17:33:40":
AM или PM: 1
Часы: 5
Часы дня: 17
Минуты: 33
Секунды: 40
*/

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Solution
{
    public static void main(String[] args)
    {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date)
    {
        //напишите тут ваш код
        DateTimeFormatter dfDate = DateTimeFormat.forPattern("dd.MM.yyyy");
        DateTimeFormatter dfTime = DateTimeFormat.forPattern("HH:mm:ss");
        DateTimeFormatter dfBoth = DateTimeFormat.forPattern("dd.MM.yyyy' 'HH:mm:ss");
        String[] value = date.split(" ");
        DateTime dateTime;
        boolean showData = false;
        boolean showTime = false;

        if (value.length == 2)
        {
            dateTime = dfBoth.parseDateTime(date);
            showData = true;
            showTime = true;
        } else if (value[0].contains("."))
        {
            dateTime = dfDate.parseDateTime(date);
            showData = true;
        } else
        {
            dateTime = dfTime.parseDateTime(date);
            showTime = true;
        }

        if (showData)
        {
            System.out.println("День: " + dateTime.getDayOfMonth());
            System.out.println("День недели: " + (dateTime.getDayOfWeek() % 7 + 1));
            System.out.println("День месяца: " + dateTime.getDayOfMonth());
            System.out.println("День года: " + dateTime.getDayOfYear());
            System.out.println("Неделя месяца: " + ((dateTime.getDayOfMonth() + dateTime.withDayOfMonth(1).getDayOfWeek() - 2) / 7 + 1));

            DateTime minYearDate = dateTime.dayOfYear().withMinimumValue();
            int weekDis = (minYearDate.getWeekyear() == dateTime.getYear()) ? 0 : 1;
            int weekOfYear = dateTime.getWeekOfWeekyear() + weekDis;
            if (weekOfYear >= 53)
                weekOfYear = 1;
            System.out.println("Неделя года: " + weekOfYear);
            //---- Стандартный метод нельзя использовать-------------
            //System.out.println("Неделя года: " + dateTime.getWeekOfWeekyear());
            System.out.println("Месяц: " + (dateTime.getMonthOfYear() - 1));
            System.out.println("Год: " + dateTime.getYear());
            System.out.println("Эра: " + dateTime.getEra());
        }
        if (showTime)
        {

            System.out.println("AM или PM: " + (dateTime.getHourOfDay() < 12 ? 0 : 1));
            System.out.println("Часы: " + (dateTime.getHourOfDay() % 12));
            System.out.println("Часы дня: " + dateTime.getHourOfDay());
            System.out.println("Минуты: " + dateTime.getMinuteOfHour());
            System.out.println("Секунды: " + dateTime.getSecondOfMinute());
        }
    }
}

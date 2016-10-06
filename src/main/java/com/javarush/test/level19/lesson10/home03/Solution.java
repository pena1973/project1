package com.javarush.test.level19.lesson10.home03;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException
    {
        String fileName1 = args[0];
        //String fileName1 = "C:\\Users\\Sid927\\Desktop\\111.txt";

        BufferedReader reader = new BufferedReader(new FileReader(fileName1));
        String line = "";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy");

        int year  = 0;
        int month = 0;
        int day = 0;
        String name;
        String stringDay;
        String stringMonth;
        String stringYear;
        String stringDate;
        while ((line = reader.readLine()) != null)
        {
            String[] arr = line.split(" ");
            year  = Integer.parseInt(arr[arr.length-1]);
            month = Integer.parseInt(arr[arr.length-2]);
            day = Integer.parseInt(arr[arr.length-3]);

            stringDay = ("0"+day);
            stringDay = stringDay.substring((stringDay.length()-2),stringDay.length());

            stringMonth = ("0"+month);
            stringMonth = stringMonth.substring((stringMonth.length()-2),stringMonth.length());

            stringYear = ("0000"+year);
            stringYear = stringYear.substring((stringYear.length()-4),stringYear.length());

            stringDate = stringDay+" "+stringMonth+" "+stringYear;

            name = line.replace(arr[arr.length-3]+" "+arr[arr.length-2]+" "+arr[arr.length-1],"");
            name = name.trim();

            PEOPLE.add(new Person(name, simpleDateFormat.parse(stringDate)));
        }


        reader.close();
    }

}

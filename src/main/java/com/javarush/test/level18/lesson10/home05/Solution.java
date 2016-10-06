package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать во второй файл
Закрыть потоки
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         String firstFileName  = reader.readLine();
         String secondFileName = reader.readLine();

        //String firstFileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
        //String secondFileName = "C:\\Users\\Sid927\\Desktop\\222.txt";
        FileInputStream file1 = new FileInputStream(firstFileName);
        FileOutputStream file2 = new FileOutputStream(secondFileName);
        ArrayList<Float> list = new ArrayList<Float>();

        String str = "";
        char sign;
        while (file1.available() > 0)
        {
            sign = (char) file1.read();
            if (sign == ' ' || file1.available() == 0)
            {
                if (file1.available() == 0)
                {
                    str = str + sign;
                }
                list.add(Float.valueOf(str));
                str = "";
            } else str = str + sign;
        }

        for (int i = 0; list.size() > i; i++)
        {
            str = str + (str.equals("")? "":" ")+Math.round(list.get(i));
        }

        file2.write(str.getBytes());

        file1.close();
        file2.close();

    }
}

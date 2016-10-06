package com.javarush.test.level18.lesson05.task02;

/* Подсчет запятых
С консоли считать имя файла
Посчитать в файле количество символов ',', количество вывести на консоль
Закрыть потоки ввода-вывода

Подсказка: нужно сравнивать с ascii-кодом символа ','
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        int sighn; // код символа
        int count = 0; // Количество
        int аscii = (int) ',';

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstFileName = reader.readLine();
        //String firstFileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
        FileInputStream file = new FileInputStream(firstFileName);

        while (file.available() > 0)
        {
            sighn = file.read();
            if (аscii == sighn) count++;
        }
        System.out.printf("%d", count);
        file.close();
        reader.close();

    }
}
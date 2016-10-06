package com.javarush.test.level19.lesson10.home08;

/* Перевертыши
1 Считать с консоли имя файла.
2 Для каждой строки в файле:
2.1 переставить все символы в обратном порядке
2.2 вывести на экран
3 Закрыть поток

Пример тела входного файла:
я - программист.
Амиго

Пример результата:
.тсиммаргорп - я
огимА
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = br.readLine();
        //String fileName1 = "C:\\Users\\Sid927\\Desktop\\111.txt";
        br.close();
        BufferedReader reader = new BufferedReader(new FileReader(fileName1));

        String lineIn = "";
        String lineOut = "";
        int count = 0;

        while ((lineIn = reader.readLine()) != null)
        {
            lineOut = "";
            char[] arr = lineIn.toCharArray();

            for (int i = 0; i < arr.length; i++)
            {
                lineOut = lineOut + arr[arr.length-1-i];
            }
            System.out.println(lineOut);
        }

    }
}

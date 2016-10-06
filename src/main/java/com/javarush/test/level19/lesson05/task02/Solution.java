package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть поток ввода.
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = br.readLine();
        //String fileName1 = "C:\\Users\\Sid927\\Desktop\\111.txt";
        br.close();

        BufferedReader reader = new BufferedReader(new FileReader(fileName1));
        String line = "";
        String buffer;
        int count = 0;
        char symb;
        while ((line = reader.readLine()) != null)
        {
            line = line.toLowerCase();

            String[] arr = line.replaceAll("\\p{Punct}", " ").split("\\s");
            for (int i = 0; i < arr.length; i++)
            {
                if (arr[i].equals("world")) count++;
            }
        }

        System.out.println(count);
        reader.close();


    }
}

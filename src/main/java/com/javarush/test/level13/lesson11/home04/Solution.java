package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести все строки в файл, каждую строчку с новой стороки.
*/

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution
{
    public static void main(String[] args) throws IOException
    {
        //add your code here
        //C:\Users\Sid927\Desktop\java\vek00.txt
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile = reader.readLine();

        FileOutputStream file = new FileOutputStream(nameFile);
        String str = "";

        StringBuilder builder = new StringBuilder();

        while (!str.equals("exit"))
        {
            str = reader.readLine();
            builder.append(str);
            builder.append(System.getProperty("line.separator"));
       }
        byte[] arr = builder.toString().getBytes();
        file.write(arr);

        file.close();
        reader.close();
    }
}


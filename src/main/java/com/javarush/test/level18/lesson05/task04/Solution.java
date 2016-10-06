package com.javarush.test.level18.lesson05.task04;

/* Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке
Закрыть потоки ввода-вывода
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        ArrayList<Integer> bytesList = new ArrayList<Integer>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstFileName  = reader.readLine();
        String secondFileName = reader.readLine();
        //String firstFileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
        //String secondFileName = "C:\\Users\\Sid927\\Desktop\\222.txt";

        FileInputStream file1 = new FileInputStream(firstFileName);
        FileOutputStream file2 = new FileOutputStream(secondFileName);

        int count = file1.available();
        while (file1.available() > 0)
        {
            bytesList.add(file1.read());
        }
        for (int i = 0; i < bytesList.size(); i++)
        {
            file2.write(bytesList.get(bytesList.size()-1 - i));
        }
        file1.close();
        file2.close();
    }
}


package com.javarush.test.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
Закрыть потоки ввода-вывода.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = br.readLine();
        String fileName2 = br.readLine();
        //String fileName1 = "C:\\Users\\Sid927\\Desktop\\111.txt";
        //String fileName2 = "C:\\Users\\Sid927\\Desktop\\222.txt";
        br.close();
        BufferedReader reader = new BufferedReader(new FileReader(fileName1));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName2));

        String line = "";
        boolean second = false;

        while ((line = reader.readLine()) != null)
        {
            line = line.replaceAll("[.]","!");
            writer.write(line);
        }

        reader.close();
        writer.close();

    }
}

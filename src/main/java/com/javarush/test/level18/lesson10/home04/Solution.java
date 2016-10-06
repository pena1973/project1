package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws IOException
    {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
          String firstFileName  = reader.readLine();
          String secondFileName = reader.readLine();

        //String firstFileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
        //String secondFileName = "C:\\Users\\Sid927\\Desktop\\222.txt";
        RandomAccessFile file0 = new RandomAccessFile(firstFileName, "r");
        RandomAccessFile file1 = new RandomAccessFile(firstFileName, "rw");
        FileInputStream file2 = new FileInputStream(secondFileName);

        int count1 = (int) file1.length();
        int count2 = file2.available();
        byte[] buffer2 = new byte[count2];
        byte[] buffer1 = new byte[count1];
        file0.read(buffer1);
        file2.read(buffer2);

        file1.write(buffer2);
        file1.write(buffer1);


        file1.close();
        file2.close();

    }
}

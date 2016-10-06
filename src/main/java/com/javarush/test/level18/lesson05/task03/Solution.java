package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки ввода-вывода
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstFileName  = reader.readLine();
        String secondFileName = reader.readLine();
        String thirdsFileName = reader.readLine();
//        String firstFileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
//        String secondFileName = "C:\\Users\\Sid927\\Desktop\\222.txt";
//        String thirdsFileName = "C:\\Users\\Sid927\\Desktop\\333.txt";
        FileInputStream file1 = new FileInputStream(firstFileName);
        FileOutputStream file2 = new FileOutputStream(secondFileName);
        FileOutputStream file3 = new FileOutputStream(thirdsFileName);
        int count = file1.available();

        int half2Count =  count/2;
        int half1Count =  count- half2Count;

        byte[] buffer = new byte[half1Count];
        file1.read(buffer);
        file2.write(buffer, 0, half1Count);

        buffer = new byte[half2Count];
        file1.read(buffer);
        file3.write(buffer, 0, half2Count);
        file1.close();
        file2.close();
        file3.close();
    }
}


package com.javarush.test.level18.lesson03.task02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* Минимальный байт
Ввести с консоли имя файла
Найти минимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода
*/

public class Solution {
    public static String firstFileName;
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        firstFileName  = reader.readLine();
        // firstFileName ="C:\\Users\\Sid927\\Desktop\\111.txt";
        FileInputStream file = new FileInputStream(firstFileName);
        int minByte = file.read();
        while (file.available()>0){
            minByte = Math.min(minByte,file.read());
        }
        file.close();
        System.out.printf("%d",minByte);
    }
}

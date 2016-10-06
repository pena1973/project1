package com.javarush.test.level18.lesson03.task01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* Максимальный байт
Ввести с консоли имя файла
Найти максимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода
*/

public class Solution {
    public static String firstFileName;


    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            firstFileName  = reader.readLine();
           // firstFileName ="C:\\Users\\Sid927\\Desktop\\111.txt";
        FileInputStream file = new FileInputStream(firstFileName);
        int maxByte = file.read();
        while (file.available()>0){
            maxByte = Math.max(maxByte,file.read());
        }

        System.out.printf("%d",maxByte);
    }
}

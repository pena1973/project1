package com.javarush.test.level18.lesson03.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* Самые частые байты
Ввести с консоли имя файла
Найти байты, которые чаше всех встречаются в файле
Вывести их на экран через пробел.
Закрыть поток ввода-вывода
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {

        ArrayList<Integer> bytes1 = new ArrayList<>();
        for (int i=0; 256>i; i++){
            bytes1.add(i,0);
        }

        int key;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstFileName  = reader.readLine();
        //String firstFileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
        FileInputStream file = new FileInputStream(firstFileName);
        // если он пустой
        boolean fileEmpty = (file.available() == 0);
        int count=0;
        int max = 0;
        //  найдем максимальную частоту появления байта
        while (file.available() > 0)
        {
            key = file.read(); // байт использую как индекс массива
            count = bytes1.get(key);
            count++;
            bytes1.set(key, count);
            max = Math.max(max, count);
        }

        //перебираем и выводим все что равно максимально встречающемуся байту
        String line="";
        for (int i=0; bytes1.size()>i; i++)
        {
            if (max == bytes1.get(i))
            {

                if (line.isEmpty())
                    line = line + i;
                else
                    line = line + " " + i;
            }
        }

        if (!fileEmpty){
        System.out.printf("%s", line);}
        file.close();
    }

}


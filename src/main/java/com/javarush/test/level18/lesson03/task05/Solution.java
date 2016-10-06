package com.javarush.test.level18.lesson03.task05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* Сортировка байт
Ввести с консоли имя файла
Считать все байты из файла.
Не учитывая повторений - отсортировать их по байт-коду в возрастающем порядке.
Вывести на экран
Закрыть поток ввода-вывода

Пример байт входного файла
44 83 44

Пример вывода
44 83
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Map<Integer, Integer> bytes = new HashMap<Integer, Integer>();
        ArrayList<Integer> bytesList = new ArrayList<Integer>();
        int key;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstFileName  = reader.readLine();
        //String firstFileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
        FileInputStream file = new FileInputStream(firstFileName);
        // если он пустой
        boolean fileEmpty = (file.available() == 0);

        // считаем файл в массиув и сразу анализируем и убиваем повторения
        // ключ в мапе уникален - если он в мапе есть,
        // то следующий байт не записываем тк он повторение

        while (file.available() > 0)
        {
            key = file.read();
            Integer count = bytes.get(key);
            if (count == null)
            {
            bytesList.add(key);
            bytes.put(key, 1);
            }
        }
        // сортируем
        Object[] bytesArray = bytesList.toArray();
        Arrays.sort(bytesArray);

        //перебираем и выводим последовательно
        String line = "";


        for (int i = 0; i<bytesArray.length; i++)
        {
                if (line.isEmpty())
                    line = line + bytesArray[i];
                else
                    line = line + " " + bytesArray[i];

        }

        if (!fileEmpty){
            System.out.printf("%s", line);}
        file.close();
    }
}

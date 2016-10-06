package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки

Пример вывода:
, 19
- 7
f 361
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        String firstFileName = args[0];
       // String firstFileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
        FileInputStream file1 = new FileInputStream(firstFileName);
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        ArrayList<Integer> list = new ArrayList<Integer>();

        char sign;
        Integer count = 0;
        int ascii;
        // выстаскиваем данные в карту - для подсчета,   в массив - для сортировки
        while (file1.available() > 0)
        {
            sign = (char) file1.read();
            ascii = sign;
            count = map.get(ascii);
            if (count==null){
                count=0;
                list.add(ascii);
            }
            map.put(ascii, ++count);
        }

        Object[] array = list.toArray();
        Arrays.sort(array);

        for (int i=0; array.length>i; i++){
            sign = (char) (int) array[i];
            count = map.get(array[i]);
         System.out.println(""+sign+" "+count);
        }
        file1.close();

    }
}
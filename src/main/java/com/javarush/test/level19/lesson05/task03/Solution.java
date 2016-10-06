package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки ввода-вывода.

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
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
            String[] arr = line.split("\\s");
            for (int i = 0; i < arr.length; i++)
            {

                  if (arr[i].replaceAll("\\d","").trim().equals("")){
                      if (second){
                          writer.write(" ");}
                      else
                          second = true;
                      writer.write(arr[i]);

                  }
            }
        }

        reader.close();
        writer.close();

    }
}

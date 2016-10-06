package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.*;

public class Solution {
      public static void main(String[] args) throws IOException
        {
            String fileName1 = args[0];
            String fileName2 = args[1];
            //String fileName1 = "C:\\Users\\Sid927\\Desktop\\111.txt";
            //String fileName2 = "C:\\Users\\Sid927\\Desktop\\222.txt";

            BufferedReader reader = new BufferedReader(new FileReader(fileName1));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName2));

            String readLine;
            String word;
            String writeLine = "";
            boolean first = true;
            while ((readLine = reader.readLine()) != null)
            {


                String[] arr = readLine.split("\\s"); // пробельный символ

                for (int i = 0; i < arr.length; i++)
                {
                    word = arr[i].trim();



                    if (word.length()>6){
                        writeLine = writeLine + ((first) ? "":",") + arr[i].trim();
                        first=false;
                    }

                }

            }
            writer.write(writeLine);
            writer.close();
            reader.close();

        }
}

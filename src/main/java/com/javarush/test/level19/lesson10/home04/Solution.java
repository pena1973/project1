package com.javarush.test.level19.lesson10.home04;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Ищем нужные строки
Считать с консоли имя файла.
Вывести в консоль все строки из файла, которые содержат всего 2 слова из списка words
Закрыть потоки

Пример: words содержит слова А, Б, В
Строки:
В Б А Д  //3 слова из words, не подходит
Д А Д    //1 слово из words, не подходит
Д А Б Д  //2 слова - подходит, выводим
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = br.readLine();
        //String fileName1 = "C:\\Users\\Sid927\\Desktop\\111.txt";
        br.close();
        BufferedReader reader = new BufferedReader(new FileReader(fileName1));

        String line = "";
        int count = 0;

        while ((line = reader.readLine()) != null)
        {
            String[] arr = line.split("\\s"); // пробельный символ
            count = 0;

            for (int i = 0; i < arr.length; i++)
            {
                for (int y = 0; y < words.size(); y++){

                if (arr[i].trim().equals(words.get(y)))
                    count++;
                }
            }
            if (count ==2) System.out.println(line);
        }

    }
}

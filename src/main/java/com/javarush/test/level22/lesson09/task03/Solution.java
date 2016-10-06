package com.javarush.test.level22.lesson09.task03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstFileName = reader.readLine();
        //String firstFileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
        BufferedReader filereader = new BufferedReader(new FileReader(firstFileName));
        String s;
        ArrayList<String> words = new ArrayList<>();

        while ((s = filereader.readLine()) != null)
        {
            String[] arrs = s.split("\\s");

            for (int i = 0; i < arrs.length; i++)
            {
                // проверка на слово перевертыш
                words.add(arrs[i]);
            }
        }
        String[] words1 = new String[words.size()];
        // перетащим в массив строк из списка
        for (int i = 0; i < words.size(); i++)
        {
            words1[i] = words.get(i);
        }

        StringBuilder result = getLine(words1);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words)
    {
        if (words.length == 1) return new StringBuilder(words[0]);

        if (words.length == 0 || words == null) return new StringBuilder();
        // отсортирую по алфавиту исходный массив
        Arrays.sort(words);

        // ликвидирую пустые строки
        ArrayList<String> wordslist = new ArrayList<>();
            for (int i = 0; i < words.length; i++)
            {
                if (words[i].equals(""))continue;
                wordslist.add(words[i]);
            }



        // создам массив цепочек результатов и массивов выборки
        // в двумерном массиве каждая строка с 0 индексом это цепочка - результат
        // а начиная с 1 и выше это массив откуда выбираем слова для каждой цепочки
        StringBuilder[][] builders = new StringBuilder[wordslist.size()][wordslist.size() + 1];
        // Далее заполняем массив
        for (int i = 0; i < builders.length; i++)
        {
            builders[i][0] = new StringBuilder(wordslist.get(i));
            for (int j = 0; j < wordslist.size(); j++)
            {
                if (i == j) builders[i][j + 1] = new StringBuilder("");
                else builders[i][j + 1] = new StringBuilder(wordslist.get(j));
            }
        }
        // Далее ФОРМИРУЕМ  ЦЕПОЧКИ РЕЗУЛЬТАТОВ на место найденого слова записываю ""

        for (int i = 0; i < builders.length; i++)
        {
            // проходов должно быть столько сколько слов м массиве
            int count = builders.length;
            while (count > 0)
            {
                count--;
                String lastChar = builders[i][0].toString().toLowerCase().substring(builders[i][0].length() - 1, builders[i][0].length());
                String firstChar = "";
                for (int j = 1; j < builders[i].length; j++)
                {
                    if (builders[i][j].toString().equals("")) continue;
                    firstChar = builders[i][j].toString().toLowerCase().substring(0, 1);

                    if (lastChar.equals(firstChar))
                    {
                        // добавили слово
                        builders[i][0].append(" ").append(builders[i][j]);
                        //стерли в массиве поиска
                        builders[i][j] = new StringBuilder("");
                        // моменяли последний символ
                        lastChar = builders[i][0].toString().toLowerCase().substring(builders[i][0].length() - 1, builders[i][0].length());
                    }
                }

            }
        }
        // осталось найти самую длинную цепочку
        int longI = 0;
        for (int i = 0; i < builders.length; i++)
        {

            longI = (builders[i][0].length() > builders[longI][0].length()) ? i : longI;
        }

        // возвращаю ее
        return builders[longI][0];
    }
}

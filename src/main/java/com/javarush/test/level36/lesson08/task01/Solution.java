package com.javarush.test.level36.lesson08.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

/* Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортировать буквы по алфавиту и вывести на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то вывести их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрыть потоки.

Пример 1 данных входного файла:
zBk yaz b-kN
Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB
Пример 2 вывода:
abc

Подсказка: использовать TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        //BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Sid927\\Desktop\\file36_8_1.txt"));
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));

        String line;
        TreeSet<String> treeSet = new TreeSet<String>();
        while ((line = reader.readLine()) != null)
        {

            line = line.replaceAll("[^a-zA-Z]", "");
            line = line.toLowerCase();
            char[] chars = line.toCharArray();
            for (char c : chars)
            {
                treeSet.add(String.valueOf(c));
            }
        }

        int i = 0;
        Iterator iterator;
        iterator = treeSet.iterator();
        while (iterator.hasNext() && i < 5)
        {
            System.out.print(iterator.next());
            i++;
        }
        reader.close();
    }
}

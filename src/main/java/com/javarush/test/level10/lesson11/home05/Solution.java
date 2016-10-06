package com.javarush.test.level10.lesson11.home05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/* Количество букв
Ввести с клавиатуры 10 строчек и подсчитать в них количество различных букв (для 33 букв алфавита).  Вывести результат на экран.
Пример вывода:
а 5
б 8
в 3
г 7
…
я 9
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //алфавит
        ArrayList<Character> alphabet = new ArrayList<Character>();
        for (int i = 0; i < 32; i++)
        {
            alphabet.add((char) ('а' + i));
        }
        alphabet.add(6, 'ё');

        //ввод строк
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++)
        {
            String s = reader.readLine();
            list.add(s.toLowerCase());
        }
        //Напишите тут ваш код

        // карта букв и количества букв
        HashMap<Character, Integer> map = new HashMap<Character, Integer>(); // ключ буква, значение число
        for (char str : alphabet)
        {
            map.put(str, 0);
        }



        String stringAll ="";
        for (String str : list)
            stringAll = stringAll+str;



            int count = 0;
         char[] arr = stringAll.toCharArray();

            for (char bl : alphabet)
            { count = 0;
                for (char blSt : arr)

                if (blSt== bl) {
                count++;
                map.put(bl, count);
                }
            }


        for (Character s : alphabet)
        {
            System.out.println(s + " " + map.get(s));
        }

    }

}

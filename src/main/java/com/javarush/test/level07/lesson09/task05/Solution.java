package com.javarush.test.level07.lesson09.task05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Удвой слова
1. Введи с клавиатуры 10 слов в список строк.
2. Метод doubleValues должен удваивать слова по принципу a,b,c -> a,a,b,b,c,c.
3. Используя цикл for выведи результат на экран, каждое значение с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws Exception {
        //read strings and init ArrayList list here - считать строки с консоли и объявить ArrayList list тут

        ArrayList<String> list = new ArrayList<String>();
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));

        for (int n = 0 ; n < 10; n++)
        {
            list.add(n, reader.readLine());
        }

        ArrayList<String> result = doubleValues(list);

        //print result - вывести на экран result
        for (String s : list)
        {
            System.out.println(s);
        }
    }

    public static ArrayList<String> doubleValues(ArrayList<String> list) {
        // add your code here - добавь код тут
        int size = list.size();
        for(int i = 0; i < size; i++){
            list.add(i + 1, list.get(i)); i++; size++;}

        return list;
    }
}

package com.javarush.test.level07.lesson04.task03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 2 массива
1. Создать массив на 10 строк.
2. Создать массив на 10 чисел.
3. Ввести с клавиатуры 10 строк, заполнить ими массив строк.
4. В каждую ячейку массива чисел записать длину строки из массива строк, индекс/номер ячейки которой совпадает с текущим
индексом из массива чисел. Вывести содержимое массива чисел на экран, каждое значение выводить с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));

        String[] arr1 = new String[10];
        int[]    arr2 = new int[10];

        for (int n = 0 ; n < 10; n++){
            arr1[n]  = reader.readLine();;
            arr2[n]  = arr1[n].length();
             }
        for (int n = 0 ; n < 10; n++){
             System.out.println(arr2[n]);
        }
    }
}

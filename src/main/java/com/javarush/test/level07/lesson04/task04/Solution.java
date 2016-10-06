package com.javarush.test.level07.lesson04.task04;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Массив из чисел в обратном порядке
1. Создать массив на 10 чисел.
2. Ввести с клавиатуры 10 чисел и записать их в массив.
3. Расположить элементы массива в обратном порядке.
4. Вывести результат на экран, каждое значение выводить с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));


        int[] arr = new int[10];
        int indx = 9;
        for (int n = 0 ; n < 10; n++){
            String num  = reader.readLine();;
            arr[indx] = Integer.parseInt(num);
            indx--;
        }



        for (int n = 0 ; n < 10; n++){
            System.out.println(arr[n]);
        }

    }
}

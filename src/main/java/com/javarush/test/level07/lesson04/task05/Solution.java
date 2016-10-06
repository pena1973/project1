package com.javarush.test.level07.lesson04.task05;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Один большой массив и два маленьких
1. Создать массив на 20 чисел.
2. Ввести в него значения с клавиатуры.
3. Создать два массива на 10 чисел каждый.
4. Скопировать большой массив в два маленьких: половину чисел в первый маленький, вторую половину во второй маленький.
5. Вывести второй маленький массив на экран, каждое значение выводить с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код
        //Напишите тут ваш код
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[20];
        for (int n = 0 ; n < 20; n++){
            String num  = reader.readLine();;
            arr[n] = Integer.parseInt(num);
         }
        int[] arr1 = new int[10];
        int[] arr2 = new int[10];

        int indx = 10;
        for (int n = 0 ; n < 10; n++){
            arr1[n] =  arr[n];
            arr2[n] =  arr[indx];
            indx++;
        }


        for (int n = 0 ; n < 10; n++){
            System.out.println(arr2[n]);
        }

    }
}

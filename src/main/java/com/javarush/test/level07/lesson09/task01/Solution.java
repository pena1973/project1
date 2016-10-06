package com.javarush.test.level07.lesson09.task01;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Три массива
1. Введи с клавиатуры 20 чисел, сохрани их в список и рассортируй по трём другим спискам:
Число делится на 3 (x%3==0), делится на 2 (x%2==0) и все остальные.
Числа, которые делятся на 3 и на 2 одновременно, например 6, попадают в оба списка.
2. Метод printList должен выводить на экран все элементы списка  с новой строки.
3. Используя метод printList выведи эти три списка на экран. Сначала тот, который для x%3, потом тот, который для x%2, потом последний.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        ArrayList<Integer> arr = new ArrayList<Integer>();

        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));

        for (int n = 0 ; n < 20; n++)
        {
            String num = reader.readLine();
            arr.add(n, Integer.parseInt(num));
        }

        ArrayList arrD2 = new ArrayList(); // делится на 2
        ArrayList arrD3 = new ArrayList(); // делится на 3
        ArrayList arrD0 = new ArrayList(); // Остальные

        for (int n = 0 ; n < arr.size() ; n++)
        {
           int numer =  arr.get(n);

            if((numer % 2) == 0){
            arrD2.add(numer);}

            if (numer % 3 == 0){
            arrD3.add(numer);}

            if ((numer%2!=0) && (numer%3!=0)){
            arrD0.add(numer);}

        }

        printList(arrD3);
        printList(arrD2);
        printList(arrD0);

    }

    public static void printList(List<Integer> list) {

        for (int n = 0 ; n < list.size() ; n++)
        System.out.println(list.get(n));
    }
}

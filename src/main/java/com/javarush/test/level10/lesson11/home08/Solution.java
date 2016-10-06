package com.javarush.test.level10.lesson11.home08;

import java.util.ArrayList;

/* Массив списков строк
Создать массив, элементами которого будут списки строк. Заполнить массив любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<String>[] arrayOfStringList =  createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList()
    {
        //Напишите тут ваш код

        ArrayList<String>[] array = new ArrayList[3];
        ArrayList<String>  strArr1 = new ArrayList<String>();
        ArrayList<String>  strArr2 = new ArrayList<String>();
        ArrayList<String>  strArr3 = new ArrayList<String>();
        strArr1.add("123");
        array[0] = strArr1;
        strArr2.add("123");
        array[1] = strArr2;
        strArr3.add("123");
        array[2] = strArr3;


        return array;
    }

    public static void printList(ArrayList<String>[] arrayOfStringList)
    {
        for (ArrayList<String> list: arrayOfStringList)
        {
            for (String s : list)
            {
                System.out.println(s);
            }
        }
    }
}
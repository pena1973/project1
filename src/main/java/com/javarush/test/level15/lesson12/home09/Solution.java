package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Solution
{
    public static void main(String[] args) throws IOException
    {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String strCom = reader.readLine(); //вся строка
        int indx = strCom.indexOf("?");           // нашли где начинаются параметры
        String str = strCom.substring(indx + 1, strCom.length());// отсекаю все кроме параметров
        String[] arr = str.split("&"); // список пар параметров

        // создам нужные переменные
        String[] par; // список частей каждого параметра - массив меняется в цикле
        // Вытаскиваю параметры и печатаю их
        for (int i = 0; i < arr.length; i++)
        {
            str = arr[i];
            par = str.split("=");
            if (i < arr.length - 1)
                System.out.print(par[0] + " ");
            else System.out.print(par[0]);
        }
            System.out.println();

            // создам нужные переменные
            double num = 0;
            String key;
            String value;
            // разбираю пары
            for (int i = 0; i < arr.length; i++)
            {
                str = arr[i];


                    par = str.split("="); // получили пару в массиве размером 2 значения
                // анализирую как разбилась пара, если параметр встроке не заполнен value пустое
                key = par[0];
                if (key.equals("obj"))// если в общей строке есть obj
                {
                if (par.length == 2) value = par[1];
                else value = "";
                // пытаюсь преобразовать тип
                try
                {
                    num = Double.parseDouble(value);
                    alert(num);
                }
                catch (Exception e)
                {
                    alert(value);
                }
            }
        }
    }
    public static void alert(double value)
    {
        System.out.println("double " + value);
    }
    public static void alert(String value)
    {
        System.out.println("String " + value);
    }
}

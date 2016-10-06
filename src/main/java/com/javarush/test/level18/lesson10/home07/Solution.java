package com.javarush.test.level18.lesson10.home07;

/* Поиск данных внутри файла
Считать с консоли имя файла
Найти в файле информацию, которая относится к заданному id, и вывести ее на экран в виде, в котором она записана в файле.
Программа запускается с одним параметром: id (int)
Закрыть потоки

В файле данные разделены пробелом и хранятся в следующей последовательности:
id productName price quantity

где id - int
productName - название товара, может содержать пробелы, String
price - цена, double
quantity - количество, int

Информация по каждому товару хранится в отдельной строке
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        String id = args[0];

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstFileName  = reader.readLine();
       // String firstFileName = "C:\\Users\\Sid927\\Desktop\\111.txt";

        BufferedReader file = new BufferedReader(new FileReader(firstFileName));

        String strFromFile;

        while ((strFromFile = file.readLine()) != null){
            String[] str = strFromFile.split(" ");
            if (id.equals(str[0])){
                System.out.println(strFromFile);}
            }
        file.close();
    }
}

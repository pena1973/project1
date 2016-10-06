package com.javarush.test.level18.lesson10.home09;

/* Файлы и исключения
Читайте с консоли имена файлов
Если файла не существует (передано неправильное имя файла), то
перехватить исключение, вывести в консоль переданное неправильное имя файла и завершить работу программы
Не забудьте закрыть все потоки
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //fileName = "C:\Users\Sid927\Desktop\111.txt";
        String fileName="";

        try
        {
            fileName = reader.readLine();
            while (!fileName.equals("exit"))
            {

                FileInputStream file = new FileInputStream(fileName);
                fileName = reader.readLine();
            }
        }
        catch (FileNotFoundException e)
        {
           System.out.println(fileName);
        }
        finally
        {
            reader.close();
        }


    }
}

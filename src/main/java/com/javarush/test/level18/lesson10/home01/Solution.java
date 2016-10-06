package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки
*/
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {

        String FileName = args[0];
        //String FileName = "C:\\Users\\Sid927\\Desktop\\111.txt";// -  записано в параметрах
        FileInputStream file = new FileInputStream(FileName);
        ArrayList<Character> alphabet = new ArrayList<Character>();

        int count = 0;
        for (char i = 'a'; i <= 'z'; i++)
            alphabet.add(i);
        while (file.available() > 0)
        {
            int i = file.read();
            char simvol = (char) i;
            if (alphabet.contains(simvol))
                count++;
        }
        System.out.println(count);
        file.close();

    }
}


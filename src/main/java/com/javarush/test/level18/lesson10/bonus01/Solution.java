package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        String key = args[0];
        String fileName = args[1];
        String fileOutputName = args[2];

//        String key = "-e";
//        String fileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
//        String fileOutputName = "C:\\Users\\Sid927\\Desktop\\222.txt";
//        String key = "-d";
//        String fileName = "C:\\Users\\Sid927\\Desktop\\222.txt";
//        String fileOutputName = "C:\\Users\\Sid927\\Desktop\\111.txt";

        // зашифровка
        if (key.equals("-e"))
        {
            // прочитали в буфер
            FileInputStream file = new FileInputStream(fileName);
            int count = file.available();

            byte[] buffer = new byte[count];
            file.read(buffer);
            file.close();

            // выводим кодами аски
            int intres;
            String strres;
            FileOutputStream file1 = new FileOutputStream(fileOutputName, true);
            for (int i = 0; buffer.length > i; i++)
            {
                intres = buffer[i];
                strres = "" + intres + " ";
                strres.getBytes();
                file1.write(strres.getBytes());
            }
            file.close();
        }

        // дешифровка
        if (key.equals("-d"))
        {
            // подготовили файл для записи
            FileOutputStream file1 = new FileOutputStream(fileOutputName, true);

            // подготовили файл для чтения
            BufferedReader file = new BufferedReader(new FileReader(fileName));

            String strFromFile;
            String[] str;
            // прочитали по строкам
            while ((strFromFile = file.readLine()) != null){
                str = strFromFile.split(" ");
                // Выводим в файл байтами

                int intres = 0;

                for (int i = 0; str.length > i; i++)
                    file1.write(Integer.parseInt(str[i]));
                }

            file1.close();
        file.close();
        }
        }
    }


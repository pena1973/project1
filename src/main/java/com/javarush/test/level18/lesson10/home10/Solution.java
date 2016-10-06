package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть все потоки ввода-вывода
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        ArrayList<String> list = new ArrayList<String>(); // это для сортировки
        HashMap<String, byte[]> map = new HashMap<String, byte[]>(); // это для хранения данных
        char[] splFileName;
        int count;

        String namePart = "";
        // Читаем файлы
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //fileName = "C:\Users\Sid927\Desktop\111.txt.part1";
        String fileNameIn = reader.readLine(); // файл из которого читаем
        String fileNameOut = "";// файл в который будем записывать
        while (!fileNameIn.equals("end"))
        {
            splFileName = fileNameIn.toCharArray();

            count = splFileName.length - 1;

            while (splFileName[count] != '.')
            {
                namePart = splFileName[count] + namePart;
                count--;
            }
            list.add(namePart);

            // Добавляем в карту считанный файл
            map.put(namePart, readFile(fileNameIn));

            // если еще не сохранено, сохраняем имя файла куда будем записывать
            if (fileNameOut.isEmpty()) fileNameOut= fileNameIn.substring(0, count);

            // готовим переменные для следующей части
            namePart = "";
            fileNameIn = reader.readLine();
        }
        reader.close();
        //сортируем имена
        Object[] array = list.toArray();
        Arrays.sort(array);
        // записываем общий файл
        FileOutputStream file = new FileOutputStream(fileNameOut, true);
        for (int i = 0; array.length > i; i++)
        {
            file.write(map.get(array[i]));
        }
        file.close();
    }

    // метод читает файл и возвращает содержимое
    static byte[] readFile(String fileName) throws IOException
    {
        FileInputStream file = new FileInputStream(fileName);
        int count = file.available();

        byte[] buffer = new byte[count];
        file.read(buffer);
        file.close();
        return buffer;
    }
}

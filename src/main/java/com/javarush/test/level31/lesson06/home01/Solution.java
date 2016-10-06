package com.javarush.test.level31.lesson06.home01;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.*;


/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution
{
    public static void main(String[] args) throws IOException
    {
        String filePath = args[0]; // полный путь к файлу fileName
        //String filePath = "C:\\Users\\Sid927\\Desktop\\тестовая\\00.txt";
        File zipFileIn = new File(filePath);

        String zipPath = args[1]; // путь к zip-архиву
        //String zipPath = "C:\\Users\\Sid927\\Desktop\\тестовая\\тестовая.zip"; // путь к zip-архиву

        Map<String, byte[]> map = new HashMap<>(); // здесь храним содержимое архива
        boolean fileExists = false; // если файл в архиве существует

        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipPath));
        ZipEntry entry;
        ByteArrayOutputStream byteStream;
        while ((entry = zipInputStream.getNextEntry()) != null)
        {
            // entry - это как бы метка на потоке zipInputStream , указание что с этого места начинается наш архив
            if (!entry.getName().equals(zipFileIn.getName()))
            {
                // читаем сам файл
                byteStream = new ByteArrayOutputStream();
                while (zipInputStream.available() > 0)
                {
                    int data = zipInputStream.read();
                    if (data != -1)
                    {
                        byteStream.write(data);
                    }
                }
                zipInputStream.closeEntry();
                byteStream.close();
                map.put(entry.toString(), byteStream.toByteArray());
            } else
            {
                fileExists = true;
                FileInputStream inputStream = new FileInputStream(filePath);
                byteStream = new ByteArrayOutputStream();
                while (inputStream.available() > 0)
                {
                    byte[] data = new byte[inputStream.available()];
                    inputStream.read(data);
                    byteStream.write(data);
                }
                inputStream.close();
                byteStream.close();
                map.put(entry.toString(), byteStream.toByteArray());
            }
        }

//        //копируем дополнительныфй файл это ошибка в условии. если файл не существует в архиве то и не добавляем.
//        if (!fileExists)
//        {
//            FileInputStream inputStream = new FileInputStream(filePath);
//            byteStream = new ByteArrayOutputStream();
//            while (inputStream.available() > 0)
//            {
//                byte[] data = new byte[inputStream.available()];
//                inputStream.read(data);
//                byteStream.write(data);
//            }
//            inputStream.close();
//            byteStream.close();
//            map.put("new/" + zipFileIn.getName(), byteStream.toByteArray());
//
//        }

        //Пишем в архив обратно
        FileOutputStream zipFileOut = new FileOutputStream(zipPath);
        ZipOutputStream zip = new ZipOutputStream(zipFileOut);
        for (Map.Entry<String, byte[]> pair : map.entrySet())
        {
            zip.putNextEntry(new ZipEntry(pair.getKey()));
            zip.write(pair.getValue());
        }


        // закрываем архив
        zip.close();
        zipFileOut.close();
    }
}

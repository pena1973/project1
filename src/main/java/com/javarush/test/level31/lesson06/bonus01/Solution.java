package com.javarush.test.level31.lesson06.bonus01;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution
{
    public static void main(String[] args) throws IOException
    {
        String resultFileName = args[0]; // имя результирующего файла resultFileName

        // запихнем в массив байт все части файла
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ArrayList<File> list = new ArrayList<>();
        for (int i = 1; i < args.length; i++)
        {
            list.add(new File(args[i]));
        }
        Collections.sort(list);
        //  по порядку
        for (int i = 0; i < list.size(); i++)
        {
            Files.copy(list.get(i).toPath(), byteArrayOutputStream);
        }

        byte[] arr = new byte[1000];

        // преобразуем массив байт в зип поток
        ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));

        FileOutputStream fileOutputStream = new FileOutputStream(resultFileName);
        int n;
        ZipEntry entry = zipInputStream.getNextEntry();
        if (entry != null)
        { // здесь почему то получаю entry=null
            while ((n = zipInputStream.read(arr)) > -1)
            {
                // n - количесво байт (оно разное, не обязательно 1000) - именно их и надо записать в поток а не весь буфер
                fileOutputStream.write(arr, 0, n);
                fileOutputStream.flush();
            }
        }

        fileOutputStream.close();
        byteArrayOutputStream.close();
        fileOutputStream.close();
        zipInputStream.close();
    }
}


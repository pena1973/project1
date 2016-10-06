package com.javarush.test.level31.lesson02.home01;

import java.io.*;
import java.util.*;

/* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*/
public class Solution
{

    public static void main(String[] args)
    {
        String path = args[0];
        File folder = new File(path); // главная папка

        String resultFileAbsolutePath = args[1]; // результат

        ArrayList fileNames = new ArrayList(); // здесь собираем имена для сортировки
        Map<String, String> mapNamePath = new HashMap<String, String>(); // здесь сопоставим пути с именами

        // разбираю папки и файлы
        recursia(folder, fileNames, mapNamePath);  // раз папка разматываем вниз

        // сортируем
        Collections.sort(fileNames);

        // переименовываем
        File resultFileAbsolutePathFile = new File(resultFileAbsolutePath);
        File allFilesContentFile = new File(resultFileAbsolutePathFile.getParent() + "/allFilesContent.txt");
        if (!resultFileAbsolutePathFile.renameTo(allFilesContentFile))
            System.out.println("Rename failed");

        // записываю в один файл
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(allFilesContentFile.getAbsolutePath());
            boolean thisIsNotFirst = false;
            for (int i = 0; i < fileNames.size(); i++)
            {
                FileInputStream fileInputStream = new FileInputStream(mapNamePath.get(fileNames.get(i)));
                byte[] arrayOfBytes = new byte[(int) fileInputStream.available()];
                fileInputStream.read(arrayOfBytes);
                fileInputStream.close();
                if (thisIsNotFirst)
                {
                    fileOutputStream.write('\n');
                } else thisIsNotFirst = true;
                fileOutputStream.write(arrayOfBytes);
            }
            fileOutputStream.close();
        }
        catch (IOException e)
        {
        }

       // удаляю пустые папки
        boolean deleted = true;
        while (deleted)
        {   deleted = false;
            recursia1(folder, deleted);  // раз папка разматываем вниз
        }
    }

    public static void recursia(File folder, ArrayList fileNames, Map mapNamePath)
    {
        for (File file : folder.listFiles())
        {
            if (file.isDirectory())
            {
                recursia(file, fileNames, mapNamePath);
            } // если это папка провалимся ниже
            else if (!file.getName().equals("resultFileAbsolutePath.txt")
                    && !file.getName().equals("allFilesContent.txt"))

            {
                if (file.length() > 50)
                {
                    file.delete();
                    System.out.println(file.getName() + " удалить"); // удаляем
                } else
                {
                    fileNames.add(file.getName()); // в список сортировки
                    mapNamePath.put(file.getName(), file.getAbsolutePath()); // храню пути  соответствующие  именам
                }
            }
        }
    }

    public static void recursia1(File folder,boolean deleted)
    {
        for (File file : folder.listFiles())
        {
            if (file.isDirectory()) // если это папка провалимся ниже
            {
                if (file.list().length == 0){
                    deleted = true;
                    file.delete();
                }
                else
                recursia1(file, deleted);
            }
        }
    }
}

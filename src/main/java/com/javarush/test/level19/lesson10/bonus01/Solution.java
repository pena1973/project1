package com.javarush.test.level19.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
[Файл 1]
строка1
строка2
строка3

[Файл 2]
строка1
строка3
строка4

[Результат - список lines]
SAME строка1
REMOVED строка2
SAME строка3
ADDED строка4
*/

public class Solution
{
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = br.readLine();
        String fileName2 = br.readLine();
        //String fileName1 = "C:\\Users\\Sid927\\Desktop\\1111.txt";
        //String fileName2 = "C:\\Users\\Sid927\\Desktop\\2222.txt";
        br.close();

        BufferedReader reader1 = new BufferedReader(new FileReader(fileName1));
        BufferedReader reader2 = new BufferedReader(new FileReader(fileName2));

        List<String> lines1 = new ArrayList<String>(); // промежуточный список для первого файла
        List<String> lines2 = new ArrayList<String>(); // промежуточный список для второго файла

        String line = "";
        while ((line = reader1.readLine()) != null)
        {
            lines1.add(line);
        }
        while ((line = reader2.readLine()) != null)
        {
            lines2.add(line);
        }

        while (lines1.size() > 0)
        {
            if (lines1.get(0).equals(lines2.get(0)))
            {
                lines.add(new LineItem(Type.SAME, lines1.get(0)));

                lines1.remove(0);
                lines2.remove(0);
                continue;
            }
            // если второй массив не закончился сравниваем со следующим значением
            // если не равно - строку удалили
            if ((lines2.size() > 1) & (!lines1.get(0).equals(lines2.get(1))))
            {
                lines.add(new LineItem(Type.REMOVED, lines1.get(0)));
                lines1.remove(0);
                continue;
            }
            // если второй массив не закончился сравниваем со следующим значением
            // если  равно - строку добавили
            if ((lines2.size() > 1) & (lines1.get(0).equals(lines2.get(1))))
            {
                lines.add(new LineItem(Type.ADDED, lines2.get(0)));
                lines2.remove(0);
                continue;
            }
            // если второй массив закончился - строку удалили
            if (lines2.size()== 1)
            {
                lines.add(new LineItem(Type.REMOVED, lines1.get(0)));
                lines1.remove(0);
                continue;
            }
        }

        // Если в массиве 2 что то осталось - оно добавлено
        lines.add(new LineItem(Type.ADDED, lines2.get(0)));

        System.out.println(lines);
    }


    public static enum Type
    {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem
    {
        public Type type;
        public String line;

        public LineItem(Type type, String line)
        {
            this.type = type;
            this.line = line;
        }
    }
}

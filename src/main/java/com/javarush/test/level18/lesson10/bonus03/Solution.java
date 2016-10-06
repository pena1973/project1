package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
      String key = args[0];
//        String key = "-d";
      String id = args[1];
//        String id = "19846";

        // считаем имя файла
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
        //String fileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
        String fileName = reader1.readLine();
        ArrayList<String> list = new ArrayList<String>();

        if (key.equals("-u"))
        {

            String productName = args[2];
            String price = args[3];
            String quantity = args[4];


//            String productName = "Шорты пляжные красные";
//            String price = "159.88";
//            String quantity = "1785";
            BufferedReader reader = new BufferedReader(new InputStreamReader((new FileInputStream(fileName))));
            String line;

            while ((line = reader.readLine()) != null)
            {
                if (!line.isEmpty())
                {
                    if (line.substring(0, 8).trim().equals(id))
                    {
                        list.add(
                                ("" + id + "        ").substring(0, 8)
                                        + (productName + "                              ").substring(0, 30)
                                        + (price + "        ").substring(0, 8)
                                        + (quantity + "    ").substring(0, 4)
                        );

                    } else
                    {
                        list.add(line);
                    }

                }
            }
            reader.close();
        }


        if (key.equals("-d"))
        {

            BufferedReader reader = new BufferedReader(new InputStreamReader((new FileInputStream(fileName))));
            String line;

            while ((line = reader.readLine()) != null)
            {
                if (!line.isEmpty())
                {
                    if (!line.substring(0, 8).trim().equals(id))
                    { list.add(line);}
                }
            }
            reader.close();
        }

        FileOutputStream file = new FileOutputStream(fileName);
        file.write(list.get(0).getBytes());
        file.close();

        file = new FileOutputStream(fileName, true);

        for (int i = 1; list.size() > i; i++)
        {
            file.write("\n".getBytes());
            file.write(list.get(i).getBytes());
        }
    }
}

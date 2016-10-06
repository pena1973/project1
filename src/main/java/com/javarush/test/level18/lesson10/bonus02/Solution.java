package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {


        String key = args[0];
        String productName = args[1];
        String price = args[2];
        String quantity = args[3];

//        String key = "-c";
//        String productName = "Шорты пляжные красные";
//        String price = "159.88";
//        String quantity = "1785";

        if (key.equals("-c")){
        // считаем имя файла
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
        //String fileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
        String fileName = reader1.readLine();

        BufferedReader reader = new BufferedReader(new InputStreamReader((new FileInputStream(fileName))));
        String line;
        long idMax = 0;
        while ((line = reader.readLine()) != null)
        {
            if(!line.isEmpty())
            idMax = Math.max(idMax, Long.parseLong(line.substring(0, 8).trim()));
        }
        idMax++;
        reader.close();

        String forWrite =
                ("" + idMax + "        ").substring(0, 8)
                        + (productName + "                              ").substring(0, 30)
                        + (price + "        ").substring(0, 8)
                        + (quantity + "    ").substring(0, 4);

        FileOutputStream file = new FileOutputStream(fileName, true);
        file.write("\n".getBytes());
        file.write(forWrite.getBytes());
    }
    }
}

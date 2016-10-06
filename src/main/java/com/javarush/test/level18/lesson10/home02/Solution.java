package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран частоту встречания пробела. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
Закрыть потоки
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        String FileName = args[0];
        //String FileName = "C:\\Users\\Sid927\\Desktop\\111.txt";// -  записано в параметрах
        FileInputStream file = new FileInputStream(FileName);

        int count = file.available();
        int countSpase = 0;

        while (file.available() > 0)
        {

            char simvol = (char) file.read();
            if (simvol ==' ') countSpase++;
        }

        double d = (double)countSpase/count*100;
        d = new BigDecimal(d).setScale(2, RoundingMode.HALF_UP).doubleValue();
        System.out.println(d);

        file.close();
    }
}

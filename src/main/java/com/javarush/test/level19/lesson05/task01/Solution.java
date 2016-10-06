package com.javarush.test.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         String fileName1 = br.readLine();
         String fileName2 = br.readLine();
        //String fileName1 = "C:\\Users\\Sid927\\Desktop\\111.txt";
        //String fileName2 = "C:\\Users\\Sid927\\Desktop\\222.txt";
        br.close();
        BufferedOutputStream file2 = new BufferedOutputStream(new FileOutputStream(fileName2));
        BufferedInputStream file1 = new BufferedInputStream(new FileInputStream(fileName1));

        int byt = 0;
        while (file1.available() > 0)
        {
            file1.read();
            if (file1.available() > 0)
            {
                byt = file1.read();

                file2.write(byt);
             //   System.out.println(byt);
            }
        }
        file1.close();
        file2.close();
    }
}

package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать содержимое третьего файла
Закрыть потоки
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstFileName  = reader.readLine();
        String secondFileName = reader.readLine();
        String thirdsFileName = reader.readLine();
 //       String firstFileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
  //      String secondFileName = "C:\\Users\\Sid927\\Desktop\\222.txt";
  //      String thirdsFileName = "C:\\Users\\Sid927\\Desktop\\333.txt";
        FileOutputStream file1 = new FileOutputStream(firstFileName,true);
        FileInputStream file2 = new FileInputStream(secondFileName);
        FileInputStream file3 = new FileInputStream(thirdsFileName);

        int count2 = file2.available();
        int count3 = file3.available();
//
        byte[] buffer2 = new byte[count2];
        byte[] buffer3 = new byte[count3];
        file2.read(buffer2);
        file1.write(buffer2);
        file3.read(buffer3);
        file1.write(buffer3);

        file1.close();
        file2.close();
        file3.close();
    }
}

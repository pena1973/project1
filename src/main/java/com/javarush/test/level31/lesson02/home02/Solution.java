package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/


public class Solution {
    public static void main(String[] args){
        try
        {
            getFileTree("C:\\Users\\Sid927\\Desktop\\тестовая");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static List<String> getFileTree(String root) throws IOException {

        List<String> list = new ArrayList<String>();
        File folderRoot = new File(root);
        Queue<File> queue = new ArrayDeque<File>();
        Collections.addAll(queue, folderRoot.listFiles());

        while (!queue.isEmpty()){
            File file = queue.poll();
            if (file.isDirectory()) {
              Collections.addAll(queue, file.listFiles());
            }
            else
            list.add(file.getAbsolutePath());
        }

        return list;
    }
}

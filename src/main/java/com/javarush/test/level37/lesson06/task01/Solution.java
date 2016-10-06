package com.javarush.test.level37.lesson06.task01;

import java.io.FileInputStream;

import java.util.Map;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* Найти класс по описанию
1. Реализует интерфейс Map
2. Используется при работе с трэдами
3. Неблокирущая версия списка с пропусками, который адаптирован для хеш-таблицы.
Про список с пропусками читать в дополнительном материале к этой лекци
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {

            //C:\Program Files\Java\jdk1.8.0_31\jre\lib\rt.jar\java\il\concurrent
            String rtJarPath = System.getProperty("java.home") + "/lib/rt.jar";
            //А потом через new JarFile(rtJarPath) без ZipInputStream.
            Class clazz = null;
            ZipInputStream zip = null;
            try
            {
                zip = new ZipInputStream(new FileInputStream(rtJarPath));
                ZipEntry e = null;
                String className;
                while (null != (e = zip.getNextEntry()))
                {
                    if (e.getName().startsWith("java/util/concurrent/") && !e.isDirectory())
                    {

                        className = e.getName().replaceAll(".class", "").replace("/", ".");
                        clazz = Class.forName(className);

                        // ищем поддержку интерфейса Map
                        if (Map.class.isAssignableFrom(clazz)){
                            // Исключаю интерфейсы
                            if (clazz.isInterface()) continue;
                            // Исключаю внутренние классы
                            if (clazz.isMemberClass()) continue;

                        //    System.out.println(clazz.getSimpleName());
                        }
                    }
                }
            }

            catch (Exception e)
            {
            }
        //Осталось два
        // ConcurrentSkipListMap
        // ConcurrentHashMap
        return ConcurrentSkipListMap.class;

        }
}

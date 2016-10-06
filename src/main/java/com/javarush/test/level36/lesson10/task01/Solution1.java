package com.javarush.test.level36.lesson10.task01;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* Найти класс по описанию
1. Реализует интерфейс Queue
2. Используется при работе с трэдами
3. Из этой очереди элементы могут быть взяты только тогда, когда они заэкспарятся, их время задержки истекло
4. Головой очереди является элемент, который заэкспарился раньше всех
*/
public class Solution1 {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Set<String> classNamesFromConcurrent = getClassNamesFromConcurrent();
        Set<Class> queueClasses = getQueuesClasses(classNamesFromConcurrent);

        for (Class clazz : queueClasses) {
            try {
                Queue queue = (Queue) clazz.newInstance();
                queue.offer(0);
            } catch (ClassCastException e) {
                if (".Delayed".equals(e.getMessage().substring(e.getMessage().lastIndexOf('.'))))
                    return clazz;
            } catch (InstantiationException | IllegalAccessException ignored) {/*NOP*/}
        }
        return null;
    }

    private static Set<Class> getQueuesClasses(Set<String> names) {
        Set<Class> result = new HashSet<>();
        for (String name : names) {
            try {
                Class clazz = Class.forName(name);
                if (Queue.class.isAssignableFrom(clazz) && !clazz.isInterface() && !clazz.isMemberClass()) {
                    result.add(clazz);
                }
            }
            catch (ClassNotFoundException ignored) {/*NOP*/}
        }
        return result;
    }

    private static Set<String> getClassNamesFromConcurrent() {
        Set<String> result = new HashSet<>();
        String rtJarPath = System.getProperty("java.home") + "/lib/rt.jar";

        try {
            JarFile jarFile = new JarFile(rtJarPath);
            Enumeration allEntries = jarFile.entries();
            while (allEntries.hasMoreElements()) {
                JarEntry entry = (JarEntry) allEntries.nextElement();
                if(entry.getName() != null && entry.getName().contains("java/util/concurrent")){
                    result.add(entry.getName().replace('/', '.').replace(".class", ""));
                }
            }
        } catch (IOException ignored) {/*NOP*/}
        return result;
    }
}
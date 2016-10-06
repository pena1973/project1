package com.javarush.test.level36.lesson10.task01;

import java.io.FileInputStream;

import java.lang.reflect.Constructor;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* Найти класс по описанию
1. Реализует интерфейс Queue
2. Используется при работе с трэдами
3. Из этой очереди элементы могут быть взяты только тогда, когда они заэкспарятся, их время задержки истекло
4. Головой очереди является элемент, который заэкспарился раньше всех
*/
//http://jdevnotes.blogspot.ru/2014/03/blockingqueue-java.html
public class Solution
{


    public static void main(String[] args)
    {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass()
    {
        //BlockingQueue - thread-safe. DelayQueue  return DelayQueue.class;
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

                    // можно через Queue.class.isAssignableFrom(clazz)
                    // ищем поддержку интерфейса Queue
                    Class[] interfaces;

                    // наличие интерфейса Queue у класса или у его родителя
                    boolean isQueueExist = false;

                    interfaces = clazz.getInterfaces();
                    for (Class interfac : interfaces)
                    {
                        if (interfac.getSimpleName().equals("Queue"))
                        {
                            isQueueExist = true;
                            //   System.out.println(clazz.getSimpleName());
                            break;
                        }
                    }

                    // ищем у родителя
                    if (!isQueueExist)
                    {
                        Class superClazz = clazz.getSuperclass();
                        if (superClazz != null)
                        {
                            while (!superClazz.getSimpleName().equals("Object"))
                            {
                                interfaces = superClazz.getInterfaces();
                                for (Class interfac : interfaces)
                                {
                                    if (interfac.getSimpleName().equals("Queue"))
                                    {
                                        isQueueExist = true;
                                        //    System.out.println(clazz.getSimpleName());
                                        break;
                                    }
                                }
                                superClazz = superClazz.getSuperclass();
                            }
                        }
                    }

                    if (isQueueExist)
                    {
                        if (clazz.isInterface()) continue;
                        if (clazz.isMemberClass()) continue;
                        //  !clazz.isInterface() && !clazz.isMemberClass() с
                        //  System.out.println(clazz.getSimpleName());

                        Constructor[] constructors = clazz.getDeclaredConstructors();

                        for (Constructor constructor : constructors)
                        {
                            constructor.setAccessible(true);
                            if (constructor.getParameterTypes().length == 0)
                            {
                                Queue queue = (Queue) constructor.newInstance();
                                try
                                {
                                    queue.add(0);
                                }
                                catch (ClassCastException e1)
                                {
                                    // System.out.println(clazz.getSimpleName());
                                    return clazz;
                                }
                                catch (IllegalStateException e1)
                                {
                                }
                            }
                        }

                    }
                }

            }
        }

        catch (Exception e)
        {
        }

        return null;

    }

}

package com.javarush.test.level36.lesson06.task01;

import java.lang.reflect.Modifier;
import java.util.Collections;
import java.lang.reflect.*;
import java.util.List;


/* Найти класс по описанию
1. Реализует интерфейс List
2. Является приватным статическим классом внутри популярного утилитного класса
3. Доступ по индексу запрещен - кидается исключение IndexOutOfBoundsException
4. Используйте рефлекшн, чтобы добраться до искомого класса
*/
//http://www.javenue.info/post/84
public class Solution
{
    public static void main(String[] args)
    {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass()
    {

        Class<?>[] aClass = Collections.class.getDeclaredClasses();
        for (int i = 0; i < aClass.length; i++)
        { System.out.println(aClass[i].getSimpleName());
            // выводим приватные поля класса
            Field[] fields = aClass[i].getDeclaredFields();
            for (Field field : fields)
            {
                Class clazz = field.getDeclaringClass();
                if (getModifiers(field.getModifiers()).contains("private"))
                {
                    Class[] interfaces;
                    // наличие интерфейса лист у поля или у его родителя
                    boolean isListExist = false;

                    interfaces = clazz.getInterfaces();
                    for (Class interfac : interfaces)
                    {
                        if (interfac.getSimpleName().equals("List"))
                        {
                            isListExist = true;
                            break;
                        }
                    }
                    // ищем у родителя
                    if (!isListExist)
                    {
                        Class superClazz = clazz.getSuperclass();
                        while (!superClazz.getSimpleName().equals("Object"))
                        {
                            interfaces = superClazz.getInterfaces();
                            for (Class interfac : interfaces)
                            {
                                if (interfac.getSimpleName().equals("List"))
                                {
                                    isListExist = true;
                                    break;
                                }
                            }
                            superClazz = superClazz.getSuperclass();
                        }
                    }

                    if (isListExist)
                    {

                        //2. Является статическим классом внутри популярного утилитного класса
                        int modifiers = clazz.getModifiers();
                        if (getModifiers(modifiers).contains("static"))
                        {

                          //   System.out.println(clazz.getSimpleName());

                            Method[] metods = clazz.getMethods();
                            for (Method method : metods)
                            {
                                if (method.getName().equals("get"))
                                {
                                    Constructor[] constructors = clazz.getDeclaredConstructors();

                                    for (Constructor constructor : constructors)
                                    {
                                        constructor.setAccessible(true);
                                        try
                                        {
                                            List list = (List) constructor.newInstance();
                                            list.get(0);
                                        }
                                        catch (IndexOutOfBoundsException e)
                                        {
                                            return clazz;
                                        }
                                        catch (Exception e)
                                        {
                                        }
                                    }
                                }
                                //    больше всего проблем было с вызовом get.
                                // В итоге оказалось нужно просто получить конструктор,
                                // изменить доступ конструктору и методу,
                                // вызвать метод через invoke(новый объект через конструктор класса, 0).
                                // Отловить исключения и проверить исключение на нужное,
                                // если все ок то еще 1 флаг в тру.
                                // Само собой все манипуляции после того, как убедился, что это именно метод get.
                                break;
                            }
                        }
                    }
                }
            }
        }


        return null;
    }

    static String getModifiers(int m)
    {
        String modifiers = "";
        if (Modifier.isPublic(m)) modifiers += "public ";
        if (Modifier.isProtected(m)) modifiers += "protected ";
        if (Modifier.isPrivate(m)) modifiers += "private ";
        if (Modifier.isStatic(m)) modifiers += "static ";
        if (Modifier.isAbstract(m)) modifiers += "abstract ";
        return modifiers;
    }


//    static String getType(Class clazz)
//    {
//        String type = clazz.isArray()
//                ? clazz.getComponentType().getSimpleName()
//                : clazz.getSimpleName();
//        if (clazz.isArray()) type += "[]";
//        return type;
//    }
}

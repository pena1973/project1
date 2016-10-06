package com.javarush.test.level08.lesson08.task04;

//import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Удалить всех людей, родившихся летом
Создать словарь (Map<String, Date>) и занести в него десять записей по принципу: «фамилия» - «дата рождения».
Удалить из словаря всех людей, родившихся летом.
*/

public class Solution
{
    public static void main(String[] args)
    {
        HashMap map = createMap();
        removeAllSummerPeople(map);

        Iterator<Map.Entry<String, Date>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String, Date> pair = iterator.next();
            Date value = pair.getValue();
            String key = pair.getKey();
            System.out.println(key+" "+value);
         }


    }
    public static HashMap<String, Date> createMap()
    {
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Сталлоне", new Date("JUNE 1 1980"));
        map.put("Сталлоне1", new Date("JUNE 2 1980"));

        map.put("Сталлоне2", new Date("MAY 1 1980"));
        map.put("Сталлоне3", new Date("AUGUST 1 1980"));

        map.put("Сталлоне4", new Date("DECEMBER 1 1980"));
        map.put("Сталлоне5", new Date("JUNE 2 1980"));

        map.put("Сталлоне6", new Date("MAY 1 1980"));
        map.put("Сталлоне7", new Date("AUGUST 1 1980"));

        map.put("Сталлоне8", new Date("DECEMBER 1 1980"));
        map.put("Сталлоне9", new Date("DECEMBER 5 1985"));

    return map;
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map)
    {
        //Напишите тут ваш код
        Iterator<Map.Entry<String, Date>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String, Date> pair = iterator.next();
            Date value = pair.getValue();
            int month = value.getMonth();
            if ((month == 5)||(month == 6)||(month == 7))
            iterator.remove();

        }

    }
}

package com.javarush.test.level17.lesson10.bonus01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution
{
    public static List<Person> allPeople = new ArrayList<Person>();

    static
    {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException
    {
        // параметры для проверки
        //      String[] args = new String[2];
        //      args[0]="-i";
        //      args[1]="Миронов";
        //      args[1]="0";
        //        args[2]="м";
        //        args[3]="15/04/1990";

        // args - это массив строк параметров
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat dateFormatOut = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        //start here - начни тут

        if (args.length < 2)
            return;

        // -c name sex bd --- добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
        if ("-c".equals(args[0]) && args.length == 4)
        {
            if ("м".equals(args[2]))
            {
                allPeople.add(Person.createMale(args[1], dateFormat.parse(args[3])));

            } else
            {
                allPeople.add(Person.createFemale(args[1], dateFormat.parse(args[3])));
            }
            System.out.println(allPeople.size() - 1);
        }
        // -u id name sex bd --- обновляет данные человека с данным id
        if ("-u".equals(args[0]) && args.length == 5)
        {
            Person person = allPeople.get(Integer.parseInt(args[1]));
            person.setName(args[2]);
            person.setSex("м".equals(args[3]) ? Sex.MALE : Sex.FEMALE);
            person.setBirthDay(dateFormat.parse(args[4]));
        }
        // -d id --- производит логическое удаление человека с id
        if ("-d".equals(args[0]) && args.length == 2)
        {
            Person person = allPeople.get(Integer.parseInt(args[1]));
            person.setName(null);
            person.setSex(null);
            person.setBirthDay(null);
        }
        // -i id --- выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)
        if ("-i".equals(args[0]) && args.length == 2)
        {
            Person p = allPeople.get(Integer.parseInt(args[1]));
            System.out.println(String.format("%s %s %s", p.getName(), p.getSex() == Sex.MALE ? "м" : "ж", dateFormatOut.format(p.getBirthDay())));
        }
    }

}

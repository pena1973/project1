package com.javarush.test.level17.lesson10.bonus02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    public static final SimpleDateFormat dateFormatOut = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException
    {

        // параметры для проверки
//            String[] args = new String[3];
//            args[0]="-d";
//            args[1]="0";
//            args[2]="1";
          //  args[2]="Миронов";
          //  args[3]="м";
          //  args[4]="15/04/1990";
          //  args[5]="1";
          //  args[6]="Спиров";
         //   args[7]="ж";
          //  args[8]="10/08/1996";

        //start here - начни тут
        String[] argsAdd    = new String[4];
        String[] argsUpdate = new String[5];
        String[] argsDell   = new String[2];
        String[] argsInfo   = new String[2];

        if (args.length < 2)
            return;

        // -c name1 sex1 bd1 name2 sex2 bd2 ... --- добавляет людей с заданными параметрами в конец allPeople, выводит id (index) на экран
        if ("-c".equals(args[0])){
            argsAdd[0] = args[0];
            for (int i = 1; i <args.length; i=i+3)
            {
                argsAdd[1] = args[i];
                argsAdd[2] = args[i + 1];
                argsAdd[3] = args[i + 2];
                peopleAdd(argsAdd);
            }
        }

        // -u id name sex bd --- обновляет данные человека с данным id
        if ("-u".equals(args[0])){
            argsUpdate[0] = args[0];

        for (int i = 1; i <args.length; i=i+4){
            argsUpdate[1] = args[i];
            argsUpdate[2] = args[i+1];
            argsUpdate[3] = args[i+2];
            argsUpdate[4] = args[i+3];
            peopleUpdate(argsUpdate);
            }
        }

        // -d id --- производит логическое удаление человека с id
        if ("-d".equals(args[0]))
        {
            argsDell[0] = args[0];

            for (int i = 1; i < args.length;  i++)
            {
                argsDell[1] = args[i];
                peopleDell(argsDell);
            }
        }
        // -i id --- выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)
        if ("-i".equals(args[0])){
            argsInfo[0] = args[0];

        for (int i = 1; i <args.length; i++){
            argsInfo[1] = args[i];
            peopleInfo(argsInfo);
            }
        }
    }

    public static synchronized void peopleAdd(String[] args) throws ParseException
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

    public static synchronized void peopleUpdate(String[] args)throws ParseException
    {

        Person person = allPeople.get(Integer.parseInt(args[1]));

        person.setName(args[2]);
        person.setSex("м".equals(args[3]) ? Sex.MALE : Sex.FEMALE);
        person.setBirthDay(dateFormat.parse(args[4]));
    }

    public static synchronized void peopleDell(String[] args)
    {
        Person person = allPeople.get(Integer.parseInt(args[1]));
        person.setName(null);
        person.setSex(null);
        person.setBirthDay(null);
    }

    public static synchronized void peopleInfo(String[] args)
    {

        Person p = allPeople.get(Integer.parseInt(args[1]));
        System.out.println(String.format("%s %s %s", p.getName(), p.getSex() == Sex.MALE ? "м" : "ж", dateFormatOut.format(p.getBirthDay())));

    }

}

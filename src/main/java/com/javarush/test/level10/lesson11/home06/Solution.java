package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }


    public static class Human
    {
        //напишите тут ваши переменные и конструкторы
        String name;
        int age;
        int tall;
        int weight;
        boolean sex;
        String hobby;

        public Human(String name)
        {
            this.name = name;
        }

        public Human(String name, int age)
        {
            this.name = name;
            this.age = age;
        }

        public Human(String name, int age, int tall)
        {
            this.name = name;
            this.age = age;
            this.tall = tall;
        }

        public Human(String name, int age, int tall, int weight)
        {
            this.name = name;
            this.age = age;
            this.tall = tall;
            this.weight = weight;
        }

        public Human(String name, int age, int tall, int weight, boolean sex)
        {
            this.name = name;
            this.age = age;
            this.tall = tall;
            this.weight = weight;
            this.sex = sex;
        }

        public Human(String name, int age, int tall, int weight, boolean sex, String hobby)
        {
            this.name = name;
            this.age = age;
            this.tall = tall;
            this.weight = weight;
            this.sex = sex;
            this.hobby = hobby;
        }

        public Human(String name, int age,  boolean sex, String hobby)
        {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.hobby = hobby;
        }
        public Human(String name,  boolean sex, String hobby)
        {
            this.name = name;
            this.sex = sex;
            this.hobby = hobby;
        }
        public Human(String name,  String hobby)
        {
            this.name = name;
            this.hobby = hobby;
        }

        public Human(String name, int age, String hobby)
        {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.hobby = hobby;
        }

    }
}

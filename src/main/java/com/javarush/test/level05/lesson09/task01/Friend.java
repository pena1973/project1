package com.javarush.test.level05.lesson09.task01;

/* Создать класс Friend
Создать класс Friend (друг) с тремя конструкторами:
- Имя
- Имя, возраст
- Имя, возраст, пол
*/

public class Friend
{
    public String name;
    public int age;
    public String sex;

    public static void main(String[] args) throws Exception
    {
        Friend cat1 = new Friend("jhf");

    }
    public  Friend(String name){
        this.name = name;
    }

    public Friend(String name,int age){
        this.name = name;
        this.age = age;
    }
    public Friend(String name,int age, String sex){
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

}
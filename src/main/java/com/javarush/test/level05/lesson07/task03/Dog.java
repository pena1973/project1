package com.javarush.test.level05.lesson07.task03;

/* Создать класс Dog
Создать класс Dog (собака) с тремя инициализаторами:
- Имя
- Имя, рост
- Имя, рост, цвет
*/

public class Dog
{
    public String name;
    public int tall;
    public String color;

    public static void main(String[] args) throws Exception
    {
        Dog cat1 = new Dog();

    }
    public  void initialize(String name){
        this.name = name;
    }

    public void initialize(String name,int tall){
        this.name = name;
        this.tall = tall;
    }
    public void initialize(String name,int age, String color){
        this.name = name;
        this.tall = tall;
        this.color = color;
    }




}

package com.javarush.test.level05.lesson05.task03;

/* Геттеры и сеттеры для класса Dog
Создать class Dog. У собаки должна быть кличка String name и возраст int age.
Создайте геттеры и сеттеры для всех переменных класса Dog.
*/

public class Dog {
   //добавьте переменные класса тут
    public String name;
    public int age;

    public static void main(String[] args)   throws Exception
    {
        Dog cat = new Dog();
        cat.setName("bfb");
        System.out.println(cat.getName());;
        cat.setAge(2);
        System.out.println(cat.getAge());;

    }
    public void setName(String name){
        this.name =  name;
    }

    public String getName(){
        return this.name;
    }
    public void setAge(int age){
        this.age =  age;
    }

    public int getAge(){
        return this.age;
    }
    //добавьте геттеры и сеттеры тут
}

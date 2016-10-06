package com.javarush.test.level06.lesson08.task01;

/* Класс Cat и статическая переменная catCount
Создать статическую переменную int catCount в классе Cat. Создай конструктор [public Cat()], в котором увеличивай данную переменную на 1.
*/

public class Cat
{
    //Напишите тут ваш код
    public static int catCount = 0;
    public Cat()
    {
        catCount++;
        System.out.println("Cat created");
    }
}

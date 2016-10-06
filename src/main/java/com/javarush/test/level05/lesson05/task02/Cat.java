package com.javarush.test.level05.lesson05.task02;

/* Реализовать метод fight
Реализовать метод boolean fight(Cat anotherCat):
реализовать механизм драки котов в зависимости от их веса, возраста и силы.
Зависимость придумать самому. Метод должен определять, выиграли ли мы (this) бой или нет,
т.е. возвращать true, если выиграли и false - если нет.
Должно выполняться условие:
если cat1.fight(cat2) = true , то cat2.fight(cat1) = false
*/

public class Cat
{
    public String name;
    public int age;
    public int weight;
    public int strength;

    public static void main(String[] args) throws Exception
    {
        Cat cat1 = new Cat();
        cat1.name = "cat1";
        cat1.weight = 4;
        cat1.age = 3;
        cat1.strength = 4;

        Cat cat2 = new Cat();

        cat2.name = "cat2";
        cat2.weight = 4;
        cat2.age = 3;
        cat2.strength = 4;
        System.out.println(cat2.fight(cat1));

        System.out.println(cat1.fight(cat2));

    }

    public Cat()
    {
    }

    public boolean fight(Cat anotherCat)
    {

        int sostThis;
        int sostAnoth;

// сила
        if (this.strength < anotherCat.strength)
        {
            sostThis = 0;//001
            sostAnoth = 0;//0
        } else if (this.strength > anotherCat.strength)
        {
            sostThis = 0;//0
            sostAnoth = 1;//0
        } else
        {
            sostThis = 0;
            sostAnoth = 0;
        }

// возраст
        if (this.age < anotherCat.age)
        {
            sostThis = sostThis + 2;//010
        } else if (this.age > anotherCat.age)
            sostAnoth = sostAnoth + 2;//010

// вес
        if (this.weight < anotherCat.weight)
        {
            sostThis = sostThis + 4; //100
        } else if (this.weight > anotherCat.weight)
        {
            sostAnoth = sostAnoth + 4; //100
        }

// имя
        if (sostAnoth > sostThis)
            return true;
        else if (sostAnoth < sostThis)
            return false;
        else
        {
            if (this.name.compareTo(anotherCat.name) > 0)
                return true;
            else
                return false;
        }

    }
}


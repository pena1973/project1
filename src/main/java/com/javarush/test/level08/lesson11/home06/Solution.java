package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {
        //Написать тут ваш код
        Human child1 = new Human("child1", false, 5);
        Human child2 = new Human("child2", false, 4);
        Human child3 = new Human("child3", false, 8);

        Human mother = new Human("mother", false, 38);
        Human father = new Human("father", true, 39);

        mother.children.add(child1);
        mother.children.add(child2);
        mother.children.add(child3);

        father.children.add(child1);
        father.children.add(child2);
        father.children.add(child3);

        Human grMother1 = new Human("grMother1", false, 58);
        Human grFather1 = new Human("grFather1", true, 59);
        grMother1.children.add(mother);
        grFather1.children.add(mother);

        Human grMother2 = new Human("grMother2", false, 48);
        Human grFather2 = new Human("grFather2", true, 69);

        grMother2.children.add(father);
        grFather2.children.add(father);

        System.out.println(grFather1.toString());
        System.out.println(grFather2.toString());
        System.out.println(grMother1.toString());
        System.out.println(grMother2.toString());
        System.out.println(mother.toString());
        System.out.println(father.toString());
        System.out.println(child1.toString());
        System.out.println(child2.toString());
        System.out.println(child3.toString());
    }

    public static class Human
    {
        //Написать тут ваш код
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children;

        public Human(String name, boolean sex, int age)
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = new ArrayList<Human>();
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }

            return text;
        }
    }
}



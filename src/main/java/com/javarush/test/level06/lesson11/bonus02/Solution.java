package com.javarush.test.level06.lesson11.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Нужно добавить в программу новую функциональность
Задача: У каждой кошки есть имя и кошка-мама. Создать класс, который бы описывал данную ситуацию. Создать два объекта: кошку-дочь и кошку-маму. Вывести их на экран.
Новая задача: У каждой кошки есть имя, кошка-папа и кошка-мама. Изменить класс Cat так, чтобы он мог описать данную ситуацию.
Создать 6 объектов: маму, папу, сына, дочь, бабушку(мамина мама) и дедушку(папин папа).
Вывести их всех на экран в порядке: дедушка, бабушка, папа, мама, сын, дочь.

Пример ввода:
дедушка Вася
бабушка Мурка
папа Котофей
мама Василиса
сын Мурчик
дочь Пушинка

Пример вывода:
Cat name is дедушка Вася, no mother, no father
Cat name is бабушка Мурка, no mother, no father
Cat name is папа Котофей, no mother, father is дедушка Вася
Cat name is мама Василиса, mother is бабушка Мурка, no father
Cat name is сын Мурчик, mother is мама Василиса, father is папа Котофей
Cat name is дочь Пушинка, mother is мама Василиса, father is папа Котофей
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

//        String motherName = reader.readLine();
//        Cat catMother = new Cat(motherName);
//
//        String daughterName = reader.readLine();
//        Cat catDaughter = new Cat(daughterName, catMother);
//
//        System.out.println(catMother);
//        System.out.println(catDaughter);

        String grFatherName = reader.readLine();
        Cat catGrFather = new Cat(grFatherName);

        String grMotherName = reader.readLine();
        Cat catGrMother = new Cat(grMotherName);

        String fatherName = reader.readLine();
        Cat catFather = new Cat(fatherName, catGrFather, null);

        String motherName = reader.readLine();
        Cat catMother = new Cat(motherName, null, catGrMother);

        String sonName = reader.readLine();
        Cat catSon = new Cat(sonName, catFather, catMother);

        String doughterName = reader.readLine();
        Cat catDoughter = new Cat(doughterName, catFather, catMother);

        System.out.println (catGrFather.toString());
        System.out.println (catGrMother.toString());
        System.out.println (catFather.toString());
        System.out.println (catMother.toString());
        System.out.println (catSon.toString());
        System.out.println (catDoughter.toString());

    }

    public static class Cat
    {
        private String name;
       // private Cat parent;
        private Cat mother;
        private Cat father;
        Cat(String name)
        {
            this.name = name;
        }

        Cat(String name, Cat father, Cat mother)
        {
            this.name = name;
          //  this.parent = parent;
            this.mother = mother;
            this.father = father;
        }

        @Override
        public String toString()
        {
            if (mother == null & father==null)
                return "Cat name is " + name + ", no mother, no father ";
            else if (mother == null & father!=null)
                return "Cat name is " + name + ", no mother, father  is " + father.name;
            else if (mother != null & father==null)
                return "Cat name is " + name + ", mother is " + mother.name+ ", no father";
            else
                return "Cat name is " + name + ", mother is " + mother.name+ ", father  is " + father.name;


        }
    }

}

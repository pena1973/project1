package com.javarush.test.level05.lesson12.home02;

/* Man and Woman
1. Внутри класса Solution создай public static классы Man и Woman.
2. У классов должны быть поля: name(String), age(int), address(String).
3. Создай конструкторы, в которые передаются все возможные параметры.
4. Создай по два объекта каждого класса со всеми данными используя конструктор.
5. Объекты выведи на экран в таком формате [name + " " + age + " " + address].
*/

public class Solution
{
    public static void main(String[] args)
    {
        // Создай по два объекта каждого класса тут

        Man man1 = new Man("ff",55,"ttttt");
        Man man2 = new Man("rrr",55,"rrrrr");

        Woman wom1 = new Woman("fсмиf",52,"trtttt");
        Woman wom2 = new Woman("fhbvnf",53,"twertttt");

        System.out.println(man1.name + " " + man1.age + " " + man1.address);
        System.out.println(man2.name + " " + man2.age + " " + man2.address);
        System.out.println(wom1.name + " " + wom1.age + " " + wom1.address);
        System.out.println(wom2.name + " " + wom2.age + " " + wom2.address);

    }

    public static class Man
    {

        public String name;
        public int age;
        public String address;


        public Man( String name,int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }
    }

    public static class Woman
    {
        public String name;
        public int age;
        public String address;


        public Woman( String name,int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }   }


}

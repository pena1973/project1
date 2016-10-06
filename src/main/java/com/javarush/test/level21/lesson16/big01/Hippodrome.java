package com.javarush.test.level21.lesson16.big01;


import java.util.ArrayList;

/**
 * Created by Sid927 on 26.06.2015.
 */
public class Hippodrome
{
    private static ArrayList<Horse> horses = new ArrayList<Horse>();
    public static Hippodrome game;

    public static void main(String[] args) throws Exception
    {  // а) Создать объект типа Hippodrome и сохранить его в переменную game.
        // б) Создать три объекта "лошадь". Имена придумай сам. Начальные скорость у всех лошадей - 3, дистанция - 0.
        // в) Добавить созданных лошадей в список лошадей ипподрома (horses).
        game = new Hippodrome();
        Horse hors1 = new Horse("Бася", 3d, 0d);
        Horse hors2 = new Horse("Вася", 3d, 0d);
        Horse hors3 = new Horse("Мася", 3d, 0d);
        horses.add(hors1);
        horses.add(hors2);
        horses.add(hors3);
        game.run();
        game.printWinner();
    }

    public ArrayList<Horse> getHorses()
    {
        return horses;
    }

    // Метод move будет управлять движением всех лошадей.
    public void move()
    {
        for (int i = 0; i <horses.size() ; i++)
        {
            horses.get(i).move();
        }
    }

    //Метод print отрисовывать их на экран.
    public void print()
    {
        for (int i = 0; i <horses.size() ; i++)
        {
            horses.get(i).print();
        }
        System.out.println();
        System.out.println();
    }

    //  метод run - управлять всем этим.
    public void run() throws Exception
    {
//        В методе run сделай цикл от 1 до 100. Это и будет наш забег.
//        В теле цикла вызываем сначала move, затем print.
//        Что-то весь цикл не отработал за долю секунды - добавь в него еще Thread.sleep(500);

        for (int i = 1; i <= 100; i++)
        {
            move();
            print();
            Thread.sleep(500);
        }
    }

    public Horse getWinner()
    {
        double bigDistans = 0d;
        Horse winner = null;
        for (int i = 0; i < horses.size(); i++)
        {
            if (bigDistans < horses.get(i).getDistance())
            {
                bigDistans = horses.get(i).getDistance();
                winner = horses.get(i);
            }

        }
        return winner;
    }

        public void printWinner(){
            System.out.printf("Winner is %s!",getWinner().getName());
        }

}

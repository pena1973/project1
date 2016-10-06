package com.javarush.test.level15.lesson12.bonus02;

/**
 * Created by Sid927 on 19.04.2015.
 */
public class LatteMaker extends DrinkMaker
{
    @Override
     void getRightCup()
    {
        System.out.println("Берем чашку для латте");
    }

    @Override
     void putIngredient()
    {
        System.out.println("Делаем кофе");
    }

    @Override
     void pour()
    {
        System.out.println("Заливаем молоком с пенкой");
    }
}

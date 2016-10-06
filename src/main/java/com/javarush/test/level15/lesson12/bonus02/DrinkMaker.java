package com.javarush.test.level15.lesson12.bonus02;

/**
 * Created by Sid927 on 19.04.2015.
 */
public abstract class DrinkMaker
{
   abstract void getRightCup();
   abstract void putIngredient();
   abstract void pour();

    void makeDrink(){
        getRightCup();
        putIngredient();
        pour();
    }
}

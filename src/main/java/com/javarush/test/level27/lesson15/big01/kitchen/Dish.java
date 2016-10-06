package com.javarush.test.level27.lesson15.big01.kitchen;

import java.util.Arrays;

public enum Dish
{
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

     private int duration;

    private Dish(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString() {
        String sb = Arrays.toString(values());
        sb = sb.substring(1, sb.length() - 1);
        return sb;
    }
}

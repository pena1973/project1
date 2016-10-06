package com.javarush.test.level13.lesson11.bonus03;

public class Robot extends AbstractRobot
{
    private String name;

    public Robot(String name)
    {
        this.name = name;
    }
    @Override
    public String getName()
    {
        return name;
    }

}


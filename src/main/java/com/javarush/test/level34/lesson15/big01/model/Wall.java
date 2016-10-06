package com.javarush.test.level34.lesson15.big01.model;


import java.awt.*;

public class Wall extends CollisionObject
{
    public Wall(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(new Color(100,30,20));
        int x1 = getX() - getWidth() / 2;
        int y1 = getY() - getHeight() / 2;
        //graphics.drawRect(x1, y1, getWidth(), getHeight());
        graphics.fillRect(x1, y1, getWidth(), getHeight());
        //graphics.fillOval(getX(),getY(),getWidth(),getHeight());

    }
}

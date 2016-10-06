package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

public class Home extends GameObject
{

    public Home(int x, int y)
    {
        super(x, y, 2, 2);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.red);
        int x1 = getX() - getWidth() / 2;
        int y1 = getY() - getHeight() / 2;

        graphics.drawOval(x1, y1, getWidth(), getHeight());
        graphics.fillOval(x1, y1, getWidth(), getHeight());
    }
}

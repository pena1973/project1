package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable
{
    public Player(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.orange);
        int x1 = getX() - getWidth() / 2;
        int y1 = getY() - getHeight() / 2;
        graphics.fillOval(x1,y1,getWidth(),getHeight());
        //graphics.fillOval(getX(),getY(),getWidth(),getHeight());

    }

    @Override
    public void move(int x, int y)
    {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);

    }
}

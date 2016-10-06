package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable
{
    public Box(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
//        graphics.setColor(Color.darkGray);
//        graphics.drawRect(getX(), getY(), getWidth(), getHeight());
//        graphics.drawLine(getX(), getY(), getX()+Model.FIELD_SELL_SIZE, getY()+Model.FIELD_SELL_SIZE);
//        graphics.drawLine(getX()+Model.FIELD_SELL_SIZE, getY(), getX(), getY()+Model.FIELD_SELL_SIZE);

        graphics.setColor(Color.darkGray);
        int x1 = getX() - getWidth() / 2;
        int y1 = getY() - getHeight() / 2;
        graphics.drawRect(x1, y1, getWidth(), getHeight());
        graphics.drawLine(x1, y1, x1+Model.FIELD_SELL_SIZE, y1+Model.FIELD_SELL_SIZE);
        graphics.drawLine(x1+Model.FIELD_SELL_SIZE, y1, x1, y1+Model.FIELD_SELL_SIZE);

    }

    @Override
    public void move(int x, int y)
    {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);

    }
}

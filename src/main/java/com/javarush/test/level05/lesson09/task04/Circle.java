package com.javarush.test.level05.lesson09.task04;

/* Создать класс Circle
Создать класс (Circle) круг, с тремя конструкторами:
- centerX, centerY, radius
- centerX, centerY, radius, width
- centerX, centerY, radius, width, color
*/

public class Circle
{

    public int centerX;
    public int centerY;
    public int radius;
    public int width;
    public String color;

    public static void main(String[] args) throws Exception
    {
        Circle cat1 = new Circle(1,2,1);

    }
    public  Circle(int centerX,int centerY, int radius){
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public Circle(int centerX,int centerY, int radius, int width){
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius  = radius;
        this.width   = width;
    }
    public Circle(int centerX,int centerY, int radius, int width, String color ){
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius  = radius;
        this.width   = width;
        this.color = color;
    }



}

package com.javarush.test.level21.lesson16.big01;

/**
 * Created by Sid927 on 26.06.2015.
 */
public class Horse
{
    private String name;
    private double speed;
    private double distance;

    public Horse(String name, double speed, double distance)
    {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    // Метод move будет управлять движением всех лошадей.
    public void move()
    {
        distance += speed*Math.random();
    }

    //Метод print отрисовывать их на экран.
    public void print()
    { int dist = (int) Math.round(distance);
        String s = "";
        for (int i = 0; i <dist ; i++)
        {
            s=s+".";
        }
        s=s+getName();
        System.out.println(s);
    }
}

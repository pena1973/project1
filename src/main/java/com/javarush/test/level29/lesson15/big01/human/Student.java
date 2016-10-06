package com.javarush.test.level29.lesson15.big01.human;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends UniversityPerson
{
    private double averageGrade;

    private Date beginningOfSession;
    private Date endOfSession;
    private int course;

    public Student(String name, int age, double averageGrade)
    {
        super(name, age);
        this.averageGrade = averageGrade;
    }

    public int getCourse()
    {
        return course;
    }

    public void live()
    {
        learn();
    }

    public void learn()
    {
    }

    public void incAverageGrade (double delta) {
        setAverageGrade(getAverageGrade() + delta);
    }


    public void setAverageGrade(double averageGrade)
    {
        this.averageGrade = averageGrade;
    }

    public void setCourse(int course)
    {
        this.course = course;
    }

    @Override
    public String getPosition()
    {
        return "Студент";
    }


    public void setBeginningOfSession(Date start) {
        beginningOfSession = start;
    }

    public void setEndOfSession(Date end) {
        endOfSession = end;
    }


    public double getAverageGrade()
    {
        return averageGrade;
    }
}
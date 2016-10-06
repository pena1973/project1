package com.javarush.test.level29.lesson15.big01.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive{
    private List<Human> children = new ArrayList<>();

    private static int nextId = 0;
    private int id;
    protected int age;
    protected String name;

  //  protected int[] size;

    protected Size size = new Size();

    public static final int FIRST = 1;
    public static final int SECOND = 2;
    public static final int THIRD = 3;
    public static final int FOURTH = 4;
    private BloodGroup bloodGroup;

    public List<Human> getChildren() {
        return  Collections.unmodifiableList(children);
    }

    public void addChild(Human child){
        children.add(child);
    }
    public void removeChild(Human child){
        children.remove(child);
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public Human(String name, int age)
    {
        this.name = name;
        this.age = age;
        this.id = nextId;
        nextId++;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return "Человек";
    }


    @Override
    public void live()
    {

    }

    public int getId() {
        return id;
    }


    public void printData() {
        System.out.println(getPosition() + ": " + name);
    }

    public void printSize() {
        System.out.println("Рост: " + size.height + " Вес: " + size.weight);
    }
    public class Size {
        public int height;
        public int weight;
    }
}
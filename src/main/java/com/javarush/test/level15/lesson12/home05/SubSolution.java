package com.javarush.test.level15.lesson12.home05;

/**
 * Created by Sid927 on 19.04.2015.
 */
public class SubSolution extends Solution
{
    public SubSolution(int a) {super(a);}
   // public SubSolution(){super();}
    public SubSolution(float a){super(a);}
    public SubSolution(double a){ super(a);}

    protected SubSolution(Integer a){super(a);}
    protected SubSolution(Float a) {super(a);}
    protected SubSolution(Double a){super(a);}

    private SubSolution(String a){}
    private SubSolution(boolean a){}
    private SubSolution(char a){}

    SubSolution(Object a){}
    SubSolution(Character a){}
    SubSolution(Boolean a){}
}

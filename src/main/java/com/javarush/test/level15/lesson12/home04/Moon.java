package com.javarush.test.level15.lesson12.home04;

/**
 * Created by Sid927 on 19.04.2015.
 */
public class Moon implements Planet
{
    private static Moon inStanCe;

    private Moon(){}

    public static Moon getInstance(){
        if(inStanCe == null){
            inStanCe = new Moon();
        }
        return inStanCe;
    }
}

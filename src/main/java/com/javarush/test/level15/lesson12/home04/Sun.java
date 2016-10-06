package com.javarush.test.level15.lesson12.home04;

/**
 * Created by Sid927 on 19.04.2015.
 */
public class Sun implements Planet
{
    private static Sun inStanCe;

    private Sun(){}

    public static Sun getInstance(){
        if(inStanCe == null){
            inStanCe = new Sun();
        }
        return inStanCe;
    }
}



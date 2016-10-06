package com.javarush.test.level15.lesson12.home04;

/**
 * Created by Sid927 on 19.04.2015.
 */
public class Earth implements Planet
{
    private static Earth inStanCe;

    private Earth(){}

    public static Earth getInstance(){
        if(inStanCe == null){
            inStanCe = new Earth();
        }
        return inStanCe;
    }
}

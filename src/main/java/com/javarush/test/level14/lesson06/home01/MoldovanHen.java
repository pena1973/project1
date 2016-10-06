package com.javarush.test.level14.lesson06.home01;

/**
 * Created by Sid927 on 17.04.2015.
 */
public class MoldovanHen extends Hen
{
    @Override
    int getCountOfEggsPerMonth()
    {
        return 20;
    }
    @Override
    public String getDescription(){
        return super.getDescription()+ " Моя страна - Moldova. Я несу "+this.getCountOfEggsPerMonth()+" яиц в месяц.";
    }
}

package com.javarush.test.level14.lesson06.home01;

//7. В каждом из четырех последних классов написать свою реализацию метода getDescription.
//        Методы должны возвращать строку вида:
//<getDescription() родительского класса>  + <" Моя страна - SSSSS. Я несу N яиц в месяц.">
//        где SSSSS - название страны
//        где N - количество яиц в месяц

/**
 * Created by Sid927 on 17.04.2015.
 */
public class RussianHen extends Hen
{
    @Override
    int getCountOfEggsPerMonth()
    {
        return 30;
    }

    @Override
    public String getDescription(){
        return (super.getDescription()+ " Моя страна - Russia. Я несу "+this.getCountOfEggsPerMonth()+" яиц в месяц.");
    }
}

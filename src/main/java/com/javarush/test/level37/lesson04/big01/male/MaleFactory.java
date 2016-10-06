package com.javarush.test.level37.lesson04.big01.male;

import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;

public class MaleFactory implements AbstractFactory
{
    public Human getPerson(int age){
        if (age<=12) return new KidBoy();
        else if (age<=19) return new TeenBoy();
        else return new Man();
    }
}

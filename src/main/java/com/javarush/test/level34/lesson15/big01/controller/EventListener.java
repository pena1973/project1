package com.javarush.test.level34.lesson15.big01.controller;

import com.javarush.test.level34.lesson15.big01.model.Direction;

public interface EventListener
{
    public void move(Direction direction);  // – передвинуть объект в определенном направлении.
    public void restart();                  // – начать заново текущий уровень.
    public void startNextLevel();           // – начать следующий уровень.
    public void levelCompleted(int level);  // – уровень с номером level завершён.

}

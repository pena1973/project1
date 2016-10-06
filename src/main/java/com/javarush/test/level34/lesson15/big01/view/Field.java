package com.javarush.test.level34.lesson15.big01.view;
import com.javarush.test.level34.lesson15.big01.controller.EventListener;
import com.javarush.test.level34.lesson15.big01.model.Direction;
import com.javarush.test.level34.lesson15.big01.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Field extends JPanel
{
    private View view;
    private EventListener eventListener;
    public Field(View view)
    {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        for (GameObject gO: view.getGameObjects().getAll()) {
            gO.draw(g);
        }
    }
    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }
    public class KeyHandler extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT: eventListener.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT: eventListener.move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP: eventListener.move(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN: eventListener.move(Direction.DOWN);
                    break;
                case KeyEvent.VK_R: eventListener.restart();
                default: break;
            }
        }
    }
}
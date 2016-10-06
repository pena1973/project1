package com.javarush.test.level32.lesson15.big01.listeners;

import com.javarush.test.level32.lesson15.big01.View;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class FrameListener extends WindowAdapter
{
    private View view;

    public FrameListener(View view)
    {
        this.view = view;
    }

    public void windowClosing(WindowEvent windowEvent){
        view.exit();
    }
}

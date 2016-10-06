package com.javarush.test.level28.lesson06.home01;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread
{
    private static AtomicInteger priority = new AtomicInteger(0);

    private void setMyPriority()
    {
        priority.getAndIncrement();
        if (priority.get() > 10)
        {
            priority.set(1);
            setPriority(1);
        } else
        {
            setPriority(priority.get());
        }
    }

    public MyThread()
    {
        setMyPriority();
    }

    public MyThread(Runnable target)
    {
        super(target);
        setMyPriority();
    }

    public MyThread(ThreadGroup group, Runnable target)
    {
        super(group, target);
        setMyPriority();
    }

    public MyThread(String name)
    {
        super(name);
        setMyPriority();
    }

    public MyThread(ThreadGroup group, String name)
    {
        super(group, name);
        setMyPriority();
    }

    public MyThread(Runnable target, String name)
    {
        super(target, name);
        setMyPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name)
    {
        super(group, target, name);
        setMyPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize)
    {
        super(group, target, name, stackSize);
        setMyPriority();
    }
}

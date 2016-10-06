package com.javarush.test.level25.lesson05.home01;

public class LoggingStateThread extends Thread
{
    private Thread currentThread;

    public LoggingStateThread(Thread target)
    {
        currentThread = target;
        setDaemon(true);

    }

    @Override
    public void run()
    {
        Thread.State currentState = currentThread.getState();
        System.out.println(currentState); // new

        while (currentState != State.TERMINATED) {
            if (currentThread.getState() != currentState){
                currentState = currentThread.getState();
                System.out.println(currentState);
            }
        }
    }

}


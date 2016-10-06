package com.javarush.test.level30.lesson06.home01;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask
{ private int i;

    public BinaryRepresentationTask(int i)
    {
        this.i = i;
    }

    @Override
    protected Object compute()
    {
        List<BinaryRepresentationTask> subTasks = new LinkedList<>();

        int a = i % 2;
        int b = i / 2;
        String result = String.valueOf(a);

        if (b > 0) {
            BinaryRepresentationTask task = new BinaryRepresentationTask(b);
            task.fork();
            subTasks.add(task);
        }

        for(BinaryRepresentationTask task : subTasks) {
            result = task.join()+ result ; // дождёмся выполнения задачи и прибавим результат
        }
        return result;

    }

}

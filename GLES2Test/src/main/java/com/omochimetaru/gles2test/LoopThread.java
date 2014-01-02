package com.omochimetaru.gles2test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omochi on 2014/01/02.
 */
public class LoopThread extends Thread {
    private List<Runnable> tasks;
    private boolean doQuit;
    private boolean pickTask(Runnable[] task){
        synchronized (this){
            while(tasks.size() == 0){
                try { wait(); }
                catch (InterruptedException e) { continue; }
            }
            task[0] = tasks.get(0);
            tasks.remove(0);
            return true;
        }
    }
    public LoopThread(){
        doQuit = false;
        tasks = new ArrayList<Runnable>();
    }
    public void post(Runnable task){
        synchronized (this){
            tasks.add(task);
            notifyAll();
        }
    }
    public void postQuit(){
        post(new Runnable() {
            @Override
            public void run() {
                doQuit = true;
            }
        });
    }

    @Override
    public void run() {
        while(!doQuit){
            Runnable[] task = new Runnable[1];
            if(!pickTask(task)){ continue; }
            task[0].run();
        }
    }
}

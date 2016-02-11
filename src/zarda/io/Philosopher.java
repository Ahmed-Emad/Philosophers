package zarda.io;

import java.util.Random;

/**
 * Created by Ahmed Emad Barakat on 136 / 16 / 15.
 * Id: 2807
 */

class Philosopher implements Runnable {

    private Random rand = new Random();

    private int id;

    private Monitor monitor;

    public Philosopher(int id, Monitor monitor) {
        this.id = id;
        this.monitor = monitor;
    }

    public void run() {
        try {
            while (!monitor.isDone(id)) {
                think();
                monitor.pickUp(id);
                eat();
                monitor.putDown(id);
            }
            System.out.println("Philosopher " + id + " has Done All Eatings.\n");
        } catch (InterruptedException e) {
            System.out.println("Philosopher " + id + " was interrupted.\n");
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking.\n");
        Thread.sleep(rand.nextInt(250));
    }

    private void eat() throws InterruptedException {
        Thread.sleep(rand.nextInt(250));
    }

}



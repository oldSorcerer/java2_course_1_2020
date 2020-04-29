package org.levelup.theads.counter;

import lombok.SneakyThrows;

public class CounterThread implements Runnable{

    private Counter counter;

    public CounterThread (Counter counter) {
        this.counter = counter;
    }

    @Override
    @SneakyThrows
    public void run() {


       // while (counter.getCounter() < 150 && !Thread.currentThread().isInterrupted()) {
        while (counter.getCounter() < 30 ) {
            if (Thread.interrupted() && counter.getCounter() > 15) {
                return;
            }

            counter.incrementCounter();
            System.out.println("Counter value from runnable " + Thread.currentThread().getName() + ": " + counter.getCounter());
            //Thread.sleep(20);
        }

    }
}

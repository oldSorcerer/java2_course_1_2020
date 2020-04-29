package org.levelup.theads.counter;


public class Counter {

    protected int counter;

    public void incrementCounter()  {
        synchronized (this) {
//            synchronized (this) {
//                counter++;
//            }
            counter = getCounter() + 1;
        }
    }

    public synchronized int getCounter () {
        return counter;
    }


}

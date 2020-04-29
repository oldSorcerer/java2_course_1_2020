package org.levelup.theads.counter;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter extends Counter {

    private ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void incrementCounter() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getCounter() {
        lock.lock();
        try {
            return counter;
        } finally {
            lock.unlock();
        }
    }


}

package org.levelup.theads.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class NonBlockingCounter extends Counter {

    private AtomicInteger atomicCounter = new AtomicInteger(0);

    @Override
    public void incrementCounter() {
        atomicCounter.incrementAndGet();
    }

    @Override
    public int getCounter() {
        return atomicCounter.get();
    }
}

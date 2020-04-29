package org.levelup.theads.queue;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadSafeQueue<T> {

    private final Queue<T> queue;
    private final int maxSize;

    private final Object notFull = new Object();
    private final Object notEmpty = new Object();

    public ThreadSafeQueue(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new LinkedList<>();
    }

    @SneakyThrows
    public void put(T task) {
        synchronized (notFull) {
            while (queue.size() == maxSize) {
                System.err.println("Producer: wait");
                notFull.wait();
                System.err.println("Producer: wake up");
            }
        }
        synchronized (notEmpty) {
            queue.add(task);
            notEmpty.notifyAll();
        }
    }

    @SneakyThrows
    public T take() {
        synchronized (notEmpty) {
            while (queue.isEmpty()) {
                System.err.println("Consumer: wait");
                notEmpty.wait();
                System.err.println("Consumer: wake up");
            }
        }
        synchronized (notFull) {
            T task = queue.remove();
            notFull.notifyAll();
            return task;
        }
    }
}

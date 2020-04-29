package org.levelup.theads.stop;

public class ThreadStop extends Thread {

    volatile boolean shutdown;

    @Override
    public  void run() {
        while (!shutdown) {
            System.out.println("I'm working...");
        }
    }

    public void shutdown () {
        shutdown = true;
    }

}

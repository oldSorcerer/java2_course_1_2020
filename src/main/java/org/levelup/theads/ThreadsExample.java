package org.levelup.theads;

import lombok.SneakyThrows;

//@SuppressWarnings("All")
public class ThreadsExample {

    @SneakyThrows
    public static void main(String[] args) {

        Thread codeAnalyzer = new Thread(new BackgroundTask());
        codeAnalyzer.setDaemon(true);
        codeAnalyzer.start();


        Thread helloWorldThread = new Thread(new HelloWorldRunnabl(), "Hello-world-thread");
        helloWorldThread.start();

        Thread counter = new CounterThead();
        counter.start();
        Thread counter2 = new CounterThead();
        counter2.start();

        System.out.println("Main thread");
        String threadName = Thread.currentThread().getName();
        System.out.println("Thread name: " + threadName);

        Thread.sleep(13_000);
        System.out.println("Main thread finished");

    }

    static class CounterThead extends Thread{

        @Override
        @SneakyThrows
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": start counter thread");
            for(int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(threadName + ": " + i);
            }
        }

    }

    static class HelloWorldRunnabl implements Runnable {

        @Override
        @SneakyThrows
        public void run() {
            Thread.sleep(4000);
            System.out.println("Hello from: " + Thread.currentThread().getName());
        }
    }

    static class BackgroundTask implements Runnable {

        @Override
        @SneakyThrows
        public void run() {
            while (true) {
                Thread.sleep(500);
                System.out.println("Code analyzer started...");
            }

        }
    }

}

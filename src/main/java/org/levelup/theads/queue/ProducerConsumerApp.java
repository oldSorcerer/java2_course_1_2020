package org.levelup.theads.queue;

public class ProducerConsumerApp {
    public static void main(String[] args) {

        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(3);

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                queue.put(i * i);
                System.err.println("Producer: " + i);

                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10 ; i++) {

                Integer task = queue.take();
                System.err.println("Consumer: " + task);

                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        consumer.start();
        producer.start();

    }
}

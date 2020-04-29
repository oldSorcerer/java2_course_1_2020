package org.levelup.theads.pool;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadPoolApp {

    @SneakyThrows
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        //Executors.newSingleThreadExecutor(); 1 thread
        //Executors.newCachedThreadPool();


        for (int i = 0; i < 100 ; i++) {
            executorService.submit(() -> System.out.println(Thread.currentThread().getName()));
        }

        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = executorService.submit(() -> {
                Random random = new Random();
                return random.nextInt(10);
            });
            futures.add(future);
        }
        for (Future<Integer> future : futures) {
            System.out.println(future.get());
        }

        executorService.shutdown();
    }

}

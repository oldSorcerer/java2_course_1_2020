package org.levelup.job.list.reflection.annotation;

public class ServiceImpl implements Service {

    @Override
    @Profiling
    public void doSomthing() {
        System.out.println("do samthing...");
    }
}

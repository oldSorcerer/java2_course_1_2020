package org.levelup.job.list.reflection.annotation;

public class ServiceFactory {
    public static Service getService() {
        return (Service) ProfilingAnnotationProcessor.process(new ServiceImpl());
    }

    public static void main(String[] args) {
        Service service = getService();
        service.doSomthing();
    }
}

package org.levelup.job.list.reflection.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProfilingAnnotationProcessor {
    public static Object process(Object object) {
        Class<?> objClass = object.getClass();
        Method[] methods = objClass.getDeclaredMethods();

        boolean hasProfilingAnnotation = false;
        for (Method method : methods) {
            Profiling annotation = method.getAnnotation(Profiling.class);
            if ( annotation != null) {
                hasProfilingAnnotation = true;
                break;

            }
        }

        if (hasProfilingAnnotation) {
            return Proxy.newProxyInstance(
                    objClass.getClassLoader(),
                    objClass.getInterfaces(),
                    new ProfilingInvocationHandler(object)
            );
        }
        return object;
    }
    static class ProfilingInvocationHandler implements InvocationHandler {
        private Object target;

        public ProfilingInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            long start = System.nanoTime();

            Object result = method.invoke(target, args);
            System.out.println("method execution time: " + (System.nanoTime() - start));

            return result;
        }
    }
}

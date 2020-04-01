package org.levelup.job.list.reflection;

import org.levelup.job.list.reflection.annotation.RandomStringAnnotationProcessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionApp {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException, InstantiationException {
        Car carObj = new Car("Rio");

        Class<?> carClass = carObj.getClass();
        //Class<?> carClass = Car.class;

        Field[] publicFields = carClass.getFields();
        for (Field publicField : publicFields) {
            System.out.println(publicField.getName());
        }

        Field modelField = carClass.getDeclaredField("model");
        System.out.println("private field name: " + modelField.getName());
        modelField.setAccessible(true);
        modelField.set(carObj, "Solaris");
        System.out.println("changed field value: " + carObj.getModel());

        Method changeModelMethod = carClass.getDeclaredMethod("changeModel", String.class);
        changeModelMethod.setAccessible(true);
        changeModelMethod.invoke(carObj, "Polo");
        System.out.println("field value afte private method invocation: " + carObj.getModel());

        Constructor<?> carConstructor = carClass.getDeclaredConstructor(String.class, String.class);
        carConstructor.setAccessible(true);
        Car refCar = (Car) carConstructor.newInstance("Lada", "Granta");
        System.out.println(refCar.getBrand() + " " + refCar.getModel());


        Car car = new Car("Polo");
        RandomStringAnnotationProcessor.process(car);
        System.out.println(car.getBrand() + " " + car.getModel());



    }
}

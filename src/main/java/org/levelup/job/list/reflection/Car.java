package org.levelup.job.list.reflection;

import org.levelup.job.list.reflection.annotation.RandomString;

public class Car {

    @RandomString(maxLength = 10)
    public String brand;
    private String model;

    public Car(String model) {
        this.model = model;
    }

    private Car(String brand, String model) {
         this.brand = brand;
         this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    private void changeModel(String model) {
        this.model = model;
    }
}

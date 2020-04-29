package org.levelup.lesson;

public class Car {
    private int number;
    public String brand;

    public Car() {
        this.number = 458;
        this.brand = "BMV";
    }

    @Override
    public String toString() {
        return "Car{" +
                "number=" + number +
                ", brand='" + brand + '\'' +
                '}';
    }
}

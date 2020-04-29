package org.levelup.lesson;

public class Animal {
    public int id;
    public String type;

    public Animal() {
        this.id = 25;
        this.type = "Bird";
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}

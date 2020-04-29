package org.levelup.lesson;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Person {
    public int id;
    public String name;
    public String lastName;

    public Person() {
        this.id = 1;
        this.name = "Jon";
        this.lastName = "Swift";
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface MethodInfo {
        String author() default "Neil";
        int dateOfCreation() default 2019;
        String purpose();
    }

    public static class Search {

        public static String name = "ru.levelup.lessons";

        public static void search2 (String name) throws ClassNotFoundException {
            Class<?> nameClass = Class.forName(name);
            System.out.println(nameClass.toString());

        }



        public static void main(String[] args) throws ClassNotFoundException {

            search2(name);

            File dir = new File ("C:\\Users\\Alex\\IdeaProjects\\java2_course_1_2020\\src\\main\\java\\org\\levelup\\job\\list\\");

            dir.getAbsolutePath();


        }
    }

    public static class Test {
        @MethodInfo(purpose = "Print Hello World")
        public void printHelloWorid() {
            System.out.println("Hello World!");
        }
    }
}

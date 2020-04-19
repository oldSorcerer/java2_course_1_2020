package org.levelup.lessons;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassFinder {

    public static void main(String[] args) {
        findClassesInPackage("org.levelup.lesson");
    }

    private static void findClassesInPackage(String packageName) {
        String pathToPackage = ClassFinder.class.getResource("").toString().substring(6).replace("/", "\\") + packageName.replace(".", "\\");
        File packageDir = new File(pathToPackage);
        if (!packageDir.exists()) {
            System.out.println("Package does not exist or is empty.");
            return;
        }
        findClassesInDirectory(packageDir);
    }

    private static void findClassesInDirectory(File directory) {
        File[] codeFiles = directory.listFiles();
        if (codeFiles == null) {
            return;
        }
        Class<?> classFromFile;
        for (File file : codeFiles) {
            if (file.isDirectory()) {
                findClassesInDirectory(file);
            } else if (file.isFile() && file.getAbsolutePath().endsWith(".class")) {
                String className = file.getAbsolutePath().substring(
                        ClassFinder.class.getResource("")
                                .toString().substring(6).replace("/", "\\").length(),
                        file.getAbsolutePath().length() - 6
                ).replace("\\", ".");
                try {
                    classFromFile = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    continue;
                }
                if (classFromFile.getDeclaredFields().length >= 1 && classFromFile.getDeclaredFields().length <= 3) {
                    try {
                        Object instance = classFromFile.newInstance();
                        Method method = classFromFile.getMethod("toString");
                        if (method.equals(Object.class.getMethod("toString"))) {
                            System.out.println("toString() method is not overridden for class: " + classFromFile.getName());
                        } else {
                            System.out.println("Found class " + classFromFile.getName());
                            System.out.println(method.invoke(instance));
                        }
                    } catch (InstantiationException | IllegalAccessException e) {
                        if (!classFromFile.isAnnotation() && !classFromFile.isEnum() && !classFromFile.isInterface()) {
                            System.out.println("Cannot create object for class: " + classFromFile.getName());
                        }
                    } catch (NoSuchMethodException e) {
                        System.out.println("toString() method does not exist for class: " + classFromFile.getName());
                    } catch (InvocationTargetException e) {
                        System.out.println("Cannot invoke toString() method for class: " + classFromFile.getName());
                    }
                } else {
                    System.out.println("Incorrect number of properties for class: " + classFromFile.getName());
                }
            }
        }
    }

}

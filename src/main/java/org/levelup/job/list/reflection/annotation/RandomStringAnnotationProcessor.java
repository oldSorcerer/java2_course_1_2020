package org.levelup.job.list.reflection.annotation;

import java.lang.reflect.Field;
import java.util.Random;

public class RandomStringAnnotationProcessor {

    public static Object process(Object object) throws IllegalAccessException {
        // сканировать пакет

        //Нужно получить обект класс
        //найти поля с анатацией
        // сгенирировать рандомное число для установки определенной строки
        // поменять значение у поля на строку

        Class<?> objClass = object.getClass();
        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            RandomString annotation = field.getAnnotation(RandomString.class);
            if (annotation != null) {
                int maxLength = annotation.maxLength();
                Random random = new Random();
                int number = random.nextInt(Brands.values().length);

                String generatedBrand = Brands.values()[number].name().toLowerCase();
                String brand = generatedBrand.substring(0, Math.min(generatedBrand.length(), maxLength));

                field.setAccessible(true);
                field.set(object, brand);
            }
        }
        return object;
    }

}

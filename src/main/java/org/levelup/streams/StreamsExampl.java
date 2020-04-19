package org.levelup.streams;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsExampl {

    public static void main(String[] args) {
        Collection<Integer> integers = new ArrayList<>();
        integers.add(5536);
        integers.add(75);
        integers.add(585);
        integers.add(55);
        integers.add(50);
        integers.add(15);


        List<Integer> sortedCollection = new ArrayList<>(integers);
        Collections.sort(sortedCollection);

        List<String> integersAsString = new ArrayList<>(integers.size());
        for (Integer integer : integers) {
            integersAsString.add(String.valueOf(integer));
        }

        Collection<Integer> sorted = integers.stream().sorted().collect(Collectors.toList());

        Collection<String> strings = integers
                .stream()
                .filter(integer -> integer > 9 && integer < 100)
                //.map(integer -> String.valueOf(integer))
                .map(String :: valueOf)
                .filter(string -> string.length() <= 3)
                .collect(Collectors.toList());

        boolean all = strings.stream().allMatch(string -> string.length() == 2);


    }
}

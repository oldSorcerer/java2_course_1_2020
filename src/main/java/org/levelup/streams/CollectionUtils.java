package org.levelup.streams;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CollectionUtils {

    //private CollectionUtils() {}

    public static Collection<String> removeLongStrings(Collection<String> collection, int naxLength) {
        return collection.stream()
                .filter(string -> string.length() <= naxLength)
                .collect(Collectors.toList());
    }


}

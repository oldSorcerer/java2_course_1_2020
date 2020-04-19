package org.levelup.streams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

class CollectionUtilsTest {

    //test<Method name>_when<Input parametres>_then<Resuil>
    @Test
    public void testRemoveLongSrting_whenCollectionEmpty_returnEmptyCollectionCopyt() {
        Collection<String> emptyCollection = new ArrayList<>();

        Collection<String> resuit = CollectionUtils.removeLongStrings(emptyCollection, 10);
        //resuit.add(" ");
        Assertions.assertTrue(resuit.isEmpty());
        Assertions.assertNotSame(emptyCollection, resuit);
    }

    @Test
    public void testRemoveLongSrting_whenCollectionIsNull_throwExeption() {
        Assertions.assertThrows(NullPointerException.class, () -> CollectionUtils.removeLongStrings(null, 10));
    }

    @Test
    public void testRemoveLongSrting_whenCollectionIsIsValid_returnFilteredCollection() {
        Collection<String> originalCollection = new ArrayList<>(Arrays.asList("String1", "Long string"));

        Collection<String> result = CollectionUtils.removeLongStrings(originalCollection, 10);
        //Assertions.assertNotEquals(originalCollection.size(), result.size());
        Assertions.assertEquals(2, originalCollection.size());
        Assertions.assertEquals(1, result.size());

        boolean isAllStringLendthLessThan10 = result.stream().allMatch(string -> string.length() <= 10);
        Assertions.assertTrue(isAllStringLendthLessThan10);
    }
}
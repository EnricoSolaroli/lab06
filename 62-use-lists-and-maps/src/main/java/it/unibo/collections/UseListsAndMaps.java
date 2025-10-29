package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int ELEMS = 100_000;
    private static final int TIME_TO_READ = 1_000;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final List<Integer> arrayListOfIntegers = new ArrayList<Integer>();
        for(int i=1000; i<2000; i++) {
            arrayListOfIntegers.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final List<Integer> linkedListOfIntegers = new LinkedList<Integer>(arrayListOfIntegers);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        int last = arrayListOfIntegers.getLast();
        arrayListOfIntegers.set(arrayListOfIntegers.size()-1, arrayListOfIntegers.getFirst());
        arrayListOfIntegers.set(0, last);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (Integer integer : arrayListOfIntegers) {
            System.out.println(integer);
        }

        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
       
        long timeArray = System.nanoTime();
       
        for (int i = 1; i <= ELEMS; i++) {
            arrayListOfIntegers.add(i);
        }
        
        timeArray = System.nanoTime() - timeArray;
        final var millisArray = TimeUnit.NANOSECONDS.toMillis(timeArray);
        System.out.println(// NOPMD
            "adding "
                + ELEMS
                + " ints to an ArrayList took "
                + timeArray
                + "ns ("
                + millisArray
                + "ms)"
        );

        long timeLinked = System.nanoTime();
       
        for (int i = 1; i <= ELEMS; i++) {
            linkedListOfIntegers.add(i);
        }
        
        timeLinked = System.nanoTime() - timeLinked;
        final var millisLinked = TimeUnit.NANOSECONDS.toMillis(timeLinked);
        System.out.println(// NOPMD
            "adding "
                + ELEMS
                + " ints to an LinkedList took "
                + timeLinked
                + "ns ("
                + millisLinked
                + "ms)"
        );

        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        long timeArrayReading = System.nanoTime();
       
        for (int i = 1; i <= TIME_TO_READ; i++) {
            arrayListOfIntegers.get(arrayListOfIntegers.size()/2);
        }
        
        timeArrayReading = System.nanoTime() - timeArrayReading;
        final var millisArrayRead = TimeUnit.NANOSECONDS.toMillis(timeArrayReading);
        System.out.println(
            "reading "
                + arrayListOfIntegers.get(arrayListOfIntegers.size()/2)
                + " "
                + TIME_TO_READ
                + " times took "
                + timeArrayReading
                + "ns ("
                + millisArrayRead
                + "ms)"
        );

        long timeLinkedReading = System.nanoTime();
       
        for (int i = 1; i <= TIME_TO_READ; i++) {
            arrayListOfIntegers.get(arrayListOfIntegers.size()/2);
        }
        
        timeLinkedReading = System.nanoTime() - timeLinkedReading;
        final var millisLinkedReading = TimeUnit.NANOSECONDS.toMillis(timeLinkedReading);
        System.out.println(
            "reading "
                + linkedListOfIntegers.get(linkedListOfIntegers.size()/2)
                + " "
                + TIME_TO_READ
                + " times took "
                + timeLinkedReading
                + "ns ("
                + millisLinkedReading
                + "ms)"
        );

        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */

        final Map<String, Long> continentPopulation = new HashMap<>();
        
        continentPopulation.put("Africa", 1_110_635_000L);
        continentPopulation.put("Americas", 972_005_000L);
        continentPopulation.put("Antarctica", 0L);
        continentPopulation.put("Asia", 4_298_723_000L);
        continentPopulation.put("Europe", 742_452_000L);
        continentPopulation.put("Oceania", 38_304_000L);

        /*
         * 8) Compute the population of the world
         */
        long totalPopulation = 0L;
        for (Long population : continentPopulation.values()) {
            totalPopulation += population;
        }  

        System.out.println("total popolation " + totalPopulation);
    }
}

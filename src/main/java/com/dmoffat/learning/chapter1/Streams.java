package com.dmoffat.learning.chapter1;

import com.dmoffat.learning.chapter1.model.Apple;
import com.dmoffat.learning.chapter1.model.Colour;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author dan
 */
public class Streams {

    private static List<Apple> apples;
    private static List<Apple> apples2;

    // Create the list to be filtered
    static {

        List<Apple> newApples = new ArrayList<>();
        List<Apple> newApples2 = new ArrayList<>();

        // Add one million elements, creating different weights and colour for each
        for(int i = 0; i < 100_000; i++) {
            Apple.Builder apple = new Apple.Builder();

            if(i <= 50_000) {
                apple.colour(Colour.GREEN);
            } else {
                apple.colour(Colour.RED);
            }

            Apple added = apple.weight(Double.valueOf(i + 100)).build();

            newApples.add(added);
            newApples2.add(added);
        }

        apples = newApples;
        apples2 = newApples2;

    }

    public static void main(String[] args) {

        List<Long> testRun1 = new ArrayList<>();
        List<Long> testRun2 = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            // Sequential processing
            long start = System.nanoTime();
            apples.stream()
                    .filter(apple -> apple.getColour() == Colour.GREEN)
                    .collect(Collectors.toList());

            long end = System.nanoTime() - start;

            testRun1.add(TimeUnit.NANOSECONDS.toMillis(end));

            // Parallel processing
            long start2 = System.nanoTime();
            apples2.parallelStream()
                    .filter(apple -> apple.getColour() == Colour.GREEN)
                    .collect(Collectors.toList());

            long end2 = System.nanoTime() - start2;

            testRun2.add(TimeUnit.NANOSECONDS.toMillis(end2));
        }

        System.out.println("Sequential took: " + testRun1.stream().mapToLong(Long::longValue).average().getAsDouble() + "ms");
        System.out.println("Parallel took: " + testRun2.stream().mapToLong(Long::longValue).average().getAsDouble() + "ms");

    }
}

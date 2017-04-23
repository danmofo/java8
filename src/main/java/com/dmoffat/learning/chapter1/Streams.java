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

        // Add a million elements, creating different weights and colour for each
        for(int i = 0; i < 1_000_000; i++) {
            Apple.Builder apple = new Apple.Builder();

            if(i <= 500_000) {
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

        // Sequential processing
        long start = System.nanoTime();
        List<Apple> greenApples = apples.stream()
                                        .filter(apple -> apple.getColour() == Colour.GREEN)
                                        .collect(Collectors.toList());
        long end = System.nanoTime() - start;
        System.out.println("Sequential took: " + TimeUnit.NANOSECONDS.toMillis(end) + "ms");

        // Parallel processing
        start = System.nanoTime();
        List<Apple> moreGreenApples = apples2.parallelStream()
                                .filter(apple -> apple.getColour() == Colour.GREEN)
                                .collect(Collectors.toList());

        end = System.nanoTime() - start;
        System.out.println("Parallel took: " + TimeUnit.NANOSECONDS.toMillis(end) + "ms");

    }
}

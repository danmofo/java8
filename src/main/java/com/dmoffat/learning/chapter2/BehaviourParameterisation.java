package com.dmoffat.learning.chapter2;

import com.dmoffat.learning.chapter1.model.Apple;
import com.dmoffat.learning.chapter1.model.Colour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author dan
 */
public class BehaviourParameterisation {
    private static List<Apple> apples;

    static {

        List<Apple> newApples = new ArrayList<>();

        // Add one million elements, creating different weights and colour for each
        for(int i = 0; i < 100_000; i++) {
            Apple.Builder apple = new Apple.Builder();

            if(i % 2 == 0) {
                apple.colour(Colour.GREEN);
            } else {
                apple.colour(Colour.RED);
            }

            newApples.add(apple.weight(Double.valueOf(i + 100)).build());
        }

        apples = newApples;

    }

    // Attempt 1 - Farmer wants to list green apples
    public static List<Apple> filterGreenApples(List<Apple> apples) {
        // Accumulator list for apples.
        List<Apple> appleList = new ArrayList<>();

        for(Apple a : apples) {
            // Select only green apples
            if(a.getColour() == Colour.GREEN) {
                appleList.add(a);
            }
        }

        return appleList;
    }

    // Attempt 2 - paramterising the colour, farmer wants to list different colours now
    public static List<Apple> filterGreenApplesTwo(List<Apple> apples, Colour colour) {
        List<Apple> appleList = new ArrayList<>();

        for(Apple a : apples) {
            if(a.getColour() == colour) {
                appleList.add(a);
            }
        }

        return appleList;
    }

    // Attempt 3 - farmer now wants to list apples based on their weight, this isn't great because it violates the DRY
    // principle. Lots of code from attempt 2 is duplicated.
    public static List<Apple> filterGreenApplesThree(List<Apple> apples, long weight) {
        List<Apple> appleList = new ArrayList<>();

        for(Apple a : apples) {
            if(a.getWeight() >= weight) {
                appleList.add(a);
            }
        }

        return appleList;
    }

    // Attempt 4, the selection criteria has now been modelled by using a predicate, is this apple green? is this apple heavier than 100g?
    public static List<Apple> filterGreenApplesFour(List<Apple> apples, ApplePredicate predicate) {
        List<Apple> appleList = new ArrayList<>();

        for(Apple a : apples) {
            if(predicate.test(a)) {
                appleList.add(a);
            }
        }

        return appleList;
    }

    // Provide multiple ways to print the apple objects
    public static void prettyPrintApple(List<Apple> apples, AppleFormatter formatter) {
        for(Apple a : apples) {
            System.out.println(formatter.accept(a));
        }
    }

    // This example goes even further, making the list generic, meaning it can filter lists of apples, Strings, and any other type.
    public static <T> List<T> filter(List<T> items, Predicate<T> predicate) {
        List<T> filteredItems = new ArrayList<>();

        for(T item : items) {
            if(predicate.test(item)) {
                filteredItems.add(item);
            }
        }

        return filteredItems;
    }


    public static void main(String[] args) {

        // Using attempt 4, the behaviour has been abstracted, and is much more flexible to requirements changing
        filterGreenApplesFour(apples, apple -> apple.getWeight() > 5000).stream().forEach(System.out::println);
        filterGreenApplesFour(apples, apple -> apple.getColour() == Colour.GREEN).stream().forEach(System.out::println);

        // Different ways to print the same list
        prettyPrintApple(apples, apple -> "Apple colour is: " + apple.getColour().toString());
        prettyPrintApple(apples, apple -> apple.toString());

        // This method can filter lists of different types
        filter(apples, apple -> apple.getColour() == Colour.RED).forEach(System.out::println);
        filter(Arrays.asList("dan", "john", "david"), s -> s.startsWith("d")).forEach(System.out::println);
    }


}

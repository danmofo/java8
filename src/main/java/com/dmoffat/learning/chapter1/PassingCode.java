package com.dmoffat.learning.chapter1;

import com.dmoffat.learning.chapter1.model.Apple;
import com.dmoffat.learning.chapter1.model.Colour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author dan
 */
public class PassingCode {
    private static List<Apple> apples = Arrays.asList(
            new Apple.Builder().weight(100d).colour(Colour.GREEN).build(),
            new Apple.Builder().weight(200d).colour(Colour.GREEN).build(),
            new Apple.Builder().weight(300d).colour(Colour.GREEN).build(),
            new Apple.Builder().weight(150d).colour(Colour.RED).build(),
            new Apple.Builder().weight(160d).colour(Colour.RED).build(),
            new Apple.Builder().weight(170d).colour(Colour.RED).build(),
            new Apple.Builder().weight(500d).colour(Colour.MIXED).build(),
            new Apple.Builder().weight(60d).colour(Colour.MIXED).build(),
            new Apple.Builder().weight(20d).colour(Colour.MIXED).build()
    );

    public static void main(String[] args) {

        System.out.println("Pre Java 8\n--------");
        // List apples weighing more than 200g - Pre Java 8
        for(Apple apple : apples) {
            if(apple.getWeight() > 200d) {
                System.out.println(apple);
            }
        }

        // List green apples under 200g
        for(Apple apple : apples) {
            if(apple.getWeight() < 200 && apple.getColour() == Colour.GREEN) {
                System.out.println(apple);
            }
        }

        // These are both hard to abstract into a common method, without resorting to copy / paste. There could be
        // several more of these, based on different requirements.

        // Java 8 lets us pass the condition as an argument - see the static methods defined below, used as follows:
        System.out.println("Java 8\n--------");
        filterApples(apples, PassingCode::isOver200).forEach(System.out::println);
        filterApples(apples, PassingCode::isGreenAndUnder200).forEach(System.out::println);

        // Using lambdas you can take this even further, which is good if it's rarely used and you
        // don't want to create a method for it.
        System.out.println("Java 8 - lambdas\n-------");
        filterApples(apples, apple -> apple.getWeight() > 200).forEach(System.out::println);
        filterApples(apples, apple -> apple.getColour() == Colour.GREEN && apple.getWeight() < 200).forEach(System.out::println);
    }

    public static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> test) {
        List<Apple> filteredApples = new ArrayList<>();
        for(Apple apple : apples) {
            // If the given predicate is true
            if(test.test(apple)) {
                filteredApples.add(apple);
            }
        }

        return filteredApples;
    }

    public static boolean isOver200(Apple apple) {
        return apple.getWeight() > 200;
    }

    public static boolean isGreenAndUnder200(Apple apple) {
        return apple.getColour() == Colour.GREEN && apple.getWeight() < 200;
    }
}

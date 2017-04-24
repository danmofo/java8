package com.dmoffat.learning.chapter2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Real world uses of behaviour parameterisation.
 *
 * 1. A sort method has been added to the List interface, so you no longer need to use Collections.sort.
 * 2. Blocks of code in Threads, this replaces the need to manually create an anonymous instance of Runnable.
 * 3. GUI event handling, for example, when a user clicks a button, do something (a behaviour). No code example for this.
 *
 * @author dan
 */
public class RealWorldExamples {
    public static void main(String[] args) {

        // Sorting with a Comparator
        List<String> names = Arrays.asList("john", "david", "kelly", "jessie", "sootie", "adam", "mandy", "damien", "daniel", "chris", "arthur", "sam");

        // Sort alphabetically
        // Using a method reference to the built in compareTo method
        names.sort(String::compareTo);
        System.out.println(names);

        // Sort alphabetically descending
        // Using a lambda expression
        names.sort((o1, o2) -> o2.compareTo(o1));
        names.sort(Comparator.reverseOrder());
        System.out.println(names);

        // Using threads
        Thread t = new Thread(() -> System.out.println("In thread one."));
        Thread t2 = new Thread(() -> System.out.println("In thread two."));
        Thread t3 = new Thread(() -> System.out.println("In thread three."));
        Thread t4 = new Thread(() -> System.out.println("In thread four."));
        Thread t5 = new Thread(() -> System.out.println("In thread five."));
        Thread t6 = new Thread(() -> System.out.println("In thread six."));
        Thread t7 = new Thread(() -> System.out.println("In thread seven."));
        Thread t8 = new Thread(() -> System.out.println("In thread eight."));
        Thread t9 = new Thread(() -> System.out.println("In thread nine."));

        t.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();

        // Pre Java 8, much more verbose
        Thread t10 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("In thread ten.");
            }
        });

        t10.run();






    }
}

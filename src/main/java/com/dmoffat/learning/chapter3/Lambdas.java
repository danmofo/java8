package com.dmoffat.learning.chapter3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * @author dan
 */
public class Lambdas {

    private static void process(Runnable runnable) {
        runnable.run();
    }

    public static void main(String[] args) throws IOException {

        // Anonymous class
        process(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous class.");
            }
        });

        // Lambda equivalent
        process(() -> System.out.println("Lambda."));

        // The execute around pattern, in resource processing, you commonly have to open a resource, do some processing on it
        // and then close the resource. The setup and cleanup phases are always similar and surround the important code that
        // does the important stuff.

        String oneLine = processFile(reader -> reader.readLine());
        String twoLines = processFile(reader -> reader.readLine() + reader.readLine());

        System.out.println(oneLine);
        System.out.println(twoLines);


        // Predicate example
        List<String> names = new ArrayList<>(Arrays.asList("dan", "dave", "donald", "devon", "harry"));

        System.out.println(filter(names, s -> s.startsWith("d")));

        // Consumer example
        forEach(names, s -> System.out.println(s));

        // Function example, map the names of the list to another list, which contains the length of each name
        List<Integer> lengths = map(names, s -> s.length());
        System.out.println(lengths);

        // Special void-compatibility rule
        Predicate<String> predicate = s -> names.add(s);
        Consumer<String> consumer = s -> names.add(s);

        // Quiz
        Runnable o = () -> {
            System.out.println("tricky example.");
        };

        // Type inference - example only!
        Comparator<String> stringComparator = (s1, s2) -> s1.compareTo(s2);
        // Can be expressed as
        Comparator<String> stringComparator1 = Comparator.naturalOrder();

        // Method references, examples of lambda expression and their equivalent method references
        Function<String, Integer> stringIntegerFunction = s -> Integer.parseInt(s);
        Function<String, Integer> stringIntegerFunction1 = Integer::parseInt;

        BiPredicate<List<String>, String> contains = (strings, s) -> strings.contains(s);
        BiPredicate<List<String>, String> contains1 = List::contains;

        // Constructor references
        Supplier<String> stringSupplier = () -> new String();
        Supplier<String> stringSupplier1 = String::new;

        // Constructor with arguments
        Supplier<String> stringSupplier2 = () -> new String("String");
        Function<String, String> stringStringFunction = String::new;
        stringStringFunction.apply("String");

    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for(T item : list) {
            result.add(function.apply(item));
        }
        return result;
    }

    private static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for(T item : list) {
            consumer.accept(item);
        }
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        ArrayList<T> filtered = new ArrayList<T>();

        for(T item : list) {
            if(predicate.test(item)) {
                filtered.add(item);
            }
        }

        return filtered;
    }

    private static String processFile(BufferedReaderProcessor bufferedReaderProcessor) throws IOException {
        File file = new File("c:/users/dan/foo.bar");

        if(!file.exists()) {
            file.createNewFile();
            Files.write(Paths.get("c:/users/dan/foo.bar"), Arrays.asList("Line one", "Line two"), Charset.forName("UTF-8"));
        }

        try(BufferedReader reader = new BufferedReader(new FileReader("c:/users/dan/foo.bar"))) {
            return bufferedReaderProcessor.process(reader);
        }
    }

}

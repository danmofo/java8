package com.dmoffat.learning.collections_refueled;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Provides the Java 7 and 8 equivalent of the same functionality.
 *
 * @author dan
 */
public class Examples {

    private static List<String> createNames() {
        return new ArrayList<>(Arrays.asList("john", "david", "kelly", "jessie", "sootie", "adam", "mandy", "damien", "daniel", "chris", "arthur", "sam"));
    }

    private static class Student implements Comparable<Student> {
        private String firstName;
        private String lastName;

        @Override
        public int compareTo(Student o) {
            return this.getLastName().compareTo(o.getLastName());
        }

        public Student(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Student{");
            sb.append("firstName='").append(firstName).append('\'');
            sb.append(", lastName='").append(lastName).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    private static void printStudents(List<Student> students) {
        for(Student student : students) {
            System.out.println(student);
        }
    }

    public static void main(String[] args) {

        // Example data
        List<String> names = createNames();
        List<String> names1 = createNames();
        List<String> names2 = createNames();
        List<String> names3 = createNames();

        Map<String, String> map = new HashMap<>();
        map.put("dan", "blonde");
        map.put("david", "brown");
        map.put("kelly", "brown");

        Map<String, Set<String>> multimap = new HashMap<>();

        List<Student> students = new ArrayList<>();
        students.add(new Student("Daniel", "Moffat"));
        students.add(new Student("David", "Smith"));
        students.add(new Student("Xena", "The Warrior Princess"));
        students.add(new Student("Kevin", "Nash"));
        students.add(new Student(null, "The Nuller"));
        students.add(new Student("Rick", "The Nuller"));
        students.add(new Student("The", "Rock"));
        students.add(new Student("Andre", "Asphalt"));

        List<Student> students1 = new ArrayList<>(students);


        /**
         * Collection.removeIf
         */

        // Java 7
        for(Iterator<String> it = names.listIterator(); it.hasNext(); ) {
            String str = it.next();

            if(str.startsWith("d")) {
                it.remove();
            }
        }
        System.out.println(names);

        // Java 8
        names1.removeIf(s -> s.startsWith("d"));
        System.out.println(names1);


        /**
         *  List.replaceAll
         */

        // Java 7
        for (int i = 0; i < names2.size(); i++) {
            names2.set(i, names2.get(i).toUpperCase());
        }
        System.out.println(names2);

        // Java 8
        names3.replaceAll(String::toUpperCase);
        System.out.println(names3);

        /**
         * Map forEach
         */

        // Java 7
        for(Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Java 8
        map.forEach((s, s2) -> System.out.println(s + ": " + s2));

        /**
         *  Multimap example
         */

        // Java 8, these are much more concise than their Java 7 counterparts

        // put(str, i)
        multimap.computeIfAbsent("dan", s -> new HashSet<>()).add("blonde");
        multimap.computeIfAbsent("dan", s -> new HashSet<>()).add("brown");
        System.out.println(multimap);

        // remove(str, i)
        multimap.computeIfPresent("dan", (s, strings) -> strings.remove("blonde") && strings.isEmpty() ? null : strings);
        System.out.println(multimap);

        // contains(str, i)
        System.out.println(multimap.getOrDefault("dan", Collections.emptySet()).contains("brown"));

        // size(), count the items in the multimap
        System.out.println(multimap.values().stream().mapToInt(Set::size).sum());

        // values(), list all values in the multimap
        System.out.println(multimap.values().stream().flatMap(Set::stream).collect(Collectors.toList()));

        /**
         * Comparators, two-level sort, sorting by last name, then by nullable first name, nulls first
         */

        // Java 7, error-prone
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int result = o1.getLastName().compareTo(o2.getLastName());

                if(result != 0) {
                    return result;
                }

                String firstName1 = o1.getFirstName();
                String firstName2 = o2.getFirstName();

                if(firstName1 == null) {
                    return firstName2 == null ? 0 : -1;
                } else {
                    return firstName2 == null ? 1 : firstName1.compareTo(firstName2);
                }
            }
        });

        printStudents(students);

        // Java 8
        System.out.println("----");
        students1.sort(Comparator.comparing(Student::getLastName)
                .thenComparing(Student::getFirstName,
                               Comparator.nullsFirst(Comparator.naturalOrder())));
        printStudents(students1);

    }
}

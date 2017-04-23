package com.dmoffat.learning.chapter1;

import java.io.File;
import java.io.FileFilter;

/**
 * Example of using a method reference.
 *
 * @author dan
 */
public class MethodReferences {

    public static void main(String[] args) {

        // Pre Java 8 list, all hidden files in a directory
        System.out.println("Pre-Java 8 \n---------");
        File[] hiddenFiles = new File("c:/users/dan").listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });

        for(File f : hiddenFiles) {
            System.out.println(f);
        }

        // Java 8 using method references
        System.out.println("Java 8 \n---------");
        File[] hiddenFiles2 = new File("c:/users/dan").listFiles(File::isHidden);

        for(File f : hiddenFiles2) {
            System.out.println(f);
        }


    }
}

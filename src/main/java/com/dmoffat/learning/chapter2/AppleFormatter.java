package com.dmoffat.learning.chapter2;

import com.dmoffat.learning.chapter1.model.Apple;

/**
 * Represents a behaviour that takes an apple and returns a formatted string
 *
 * @author dan
 */
public interface AppleFormatter {
    String accept(Apple apple);
}

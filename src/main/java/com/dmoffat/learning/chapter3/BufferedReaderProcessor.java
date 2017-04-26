package com.dmoffat.learning.chapter3;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author dan
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader reader) throws IOException;
}

package com.stream;

import com.google.common.annotations.VisibleForTesting;

import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        Stream.of(1,2,3,4,5,6).limit(4).forEach(System.out::println);
    }
}

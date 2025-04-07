package com.programmingtechie.orderservice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.util.stream.Stream;

public class StreamUtil {
    /**
     * The main method which serves as the entry point for the StreamUtil class.
     * It prints each word from the wordStream to the console.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(StreamUtil.class.getName());

        log.info(Runtime.getRuntime().availableProcessors() + " cores");
        long startTime = System.currentTimeMillis();
        wordStream().filter(w -> w.startsWith("1")).forEach(System.out::println);
        long endTime = System.currentTimeMillis();
        log.info("Time taken: " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        wordStream().parallel().filter(w -> w.startsWith("1")).forEach(System.out::println);
        endTime = System.currentTimeMillis();
        log.info("Time taken by parallel: " + (endTime - startTime));


    }
    /**
     * A utility method to produce a Stream of words.
     *
     * @return A Stream of words
     */
    public static Stream<String> wordStream() {
        return Stream.of("1","2","3","4","5", "6", "7", "8", "9", "10" ,"11", "12", "13", "14", "15", "16", "17", "18", "19", "20");
    } 
}

package com.programmingtechie.orderservice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class DemoUtil {

   private static Logger logger = LoggerFactory.getLogger(DemoUtil.class.getName());
    public static void main(String[] args) {
        logger.info("Stream");
        StreamUtil.wordStream()
                .map(w -> w.toUpperCase())
                .forEach(w -> System.out.println(w));


        logger.info("Reactive");
        ReactiveUtil.wordStreamReactive()
                .map(w -> w.toUpperCase())
                .subscribe(w -> System.out.println(w));

        logger.info("DEMO");
        Stream.of(1, 2, 3, 4, 5)
                .peek(i -> logger.info("Processing: " + i))
                .skip(2)
                .peek(i -> logger.info("Processing: " + i))
                .forEach(i -> logger.info("Processed: " + i));

    }
}

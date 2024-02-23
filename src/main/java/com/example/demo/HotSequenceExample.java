package com.example.demo;

import ch.qos.logback.classic.Logger;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class HotSequenceExample {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> hotFlux = Flux.fromStream(Stream.of("RED", "YELLOW", "PINK"))
                .delayElements(Duration.ofSeconds(1)).share();
                // share() 원본 Flux를 여러 Subscriber가 공유한다.

        hotFlux.subscribe(country -> System.out.println("# Subscriber1 = " + country));
        Thread.sleep(2500);
        hotFlux.subscribe(country -> System.out.println("# Subscriber2 = " + country));
        Thread.sleep(2500);
    }
}

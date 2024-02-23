package com.example.demo.section10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SchedulerOperatorExample06 {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1, 3, 5, 7}) // a thread
                .doOnNext(data -> log.info(String.valueOf(data))) // a thread
                .publishOn(Schedulers.parallel()) // b thread
                .filter(data -> data > 3) // b thread
                .doOnNext(data -> log.info(String.valueOf(data))) // b thread
                .subscribeOn(Schedulers.boundedElastic()) // a thread
                .map(data -> data + 10) // b thread
                .doOnNext(data -> log.info(String.valueOf(data))) // b thread
                .subscribe(data -> log.info(String.valueOf(data))); // b thread

        Thread.sleep(500);
    }
}

package com.example.demo.section10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SchedulerOperatorExample04 {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1, 3, 5, 7}) // a thread
                .subscribeOn(Schedulers.boundedElastic()) // a thread
                .filter(data -> data > 3) // a thread
                .doOnNext(data -> log.info(String.valueOf(data)))
                .map(data -> data + 10) // a thread
                .doOnNext(data -> log.info(String.valueOf(data)))
                .subscribe(data -> log.info(String.valueOf(data))); // a thread

        Thread.sleep(500);
    }
}

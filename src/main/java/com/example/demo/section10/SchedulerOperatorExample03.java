package com.example.demo.section10;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulerOperatorExample03 {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1, 3, 5, 7}) // main thread
                .publishOn(Schedulers.parallel()) // a thread
                .filter(data -> data > 3) // main thread
                .map(data -> data + 10) // a thread
                .publishOn(Schedulers.parallel()) // b thread
                .subscribe(data -> System.out.println("data = " + data)); // b thread

        Thread.sleep(2500);
    }
}

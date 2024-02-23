package com.example.demo.section10;

import reactor.core.publisher.Flux;

/*
* Sequence의 operator 체인에서 최초의 쓰레드는 subscribe()가
* 호출되는 scope에 있는 쓰레드이다.
*/
public class SchedulerOperatorExample01 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7}) // main thread
                .filter(data -> data > 3) // main thread
                .map(data -> data + 10) // main thread
                .subscribe(data -> System.out.println("data = " + data)); // main thread
    }
}

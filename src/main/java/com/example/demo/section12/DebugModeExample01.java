package com.example.demo.section12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.util.Logger;

@Slf4j
public class DebugModeExample01 {
    public static void main(String[] args) {
        //Hooks.onOperatorDebug();

        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x/y)
                //.checkpoint("zipWith", true)
                .map(num -> num+2)
                //.checkpoint("map", true)
                .log()
                .subscribe(
                        data -> log.info(String.valueOf(data)),
                        error -> log.error("# onError:", error)
                );
    }
}

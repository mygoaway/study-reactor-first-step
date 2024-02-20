package com.example.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.Locale.ROOT;

public class HelloReactor {
    public static void main(String[] args) {
        // 1. Mono 기본 개념 예제
        // Mono는 1개의 데이터를 생성해서 emit 한다.
        Mono.just("Hello Reactor")
                .subscribe(message -> System.out.println(message));

        // 2. Mono 기본 개념 예제
        // Mono는 원본 데이터의 emit 없이 onComplete signal 만 emit 한다.
        Mono.empty()
                .subscribe(
                        // data가 있는 경우, 첫번째 람다가 처리
                        data -> System.out.println("# emitted data = " + data),
                        // 에러가 발생한 경우, 두번째 람다가 처리
                        error -> {},
                        // onComplete signal 전달하는 경우, 세번째 람다가 처리
                        () -> System.out.println("# emitted onComplete signal")
                );


        // 3. Flux 기본 예제
        // Flux는 1개 이상 이상의 데이터를 생성해서 emit 한다.
        Flux<String> sequence = Flux.just("Hello", "Reactor");
        sequence
                .map(data -> data.toUpperCase(ROOT))
                .subscribe(message -> System.out.println(message));

        Flux.just(6, 9, 13)
                .map(num -> num % 2)
                .subscribe(remainder -> System.out.println("remainder = " + remainder));

        // 4. Flux에서의 Operator 체인 사용 예제
        Flux.fromArray(new Integer[]{3, 6, 7, 9})
                .filter(num -> num > 6)
                .map(num -> num * 2)
                .subscribe(multiply -> System.out.println("multiply = " + multiply));

        // 5. 2개의 Mono를 연결해서 Flux로 변환하는 예제
        Flux<Object> flux =
                Mono.justOrEmpty(null)
                .concatWith(Mono.justOrEmpty("Jobs"));
        flux.subscribe(data -> System.out.println("data = " + data));

        // 6. 여러개의 Flux를 연결해서 하나의 Flux로 결합하는 예제
        Flux.concat(
                Mono.just("Venus"),
                Flux.just("Earth"),
                Flux.just("Mars"))
                .collectList() // Mono<List<String>> 형태로 담김
                .subscribe(planetList -> System.out.println("planetList = " + planetList));
    }
}

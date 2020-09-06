package com.hzw.reactor;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 同步使用的场景
 */
public class TestSync {
  public static void main(String[] args) {
    String[] arr = new String[] {"111", "222"};
    //发布者【被观察者】
    Flux<String> flux1 = Flux.just(arr);
    //订阅者【观察者，打印】
    flux1.subscribe(System.out::println);

    System.out.println("------");

    //发布者【被观察者】
    Flux<String> flux2 = Flux.just("qqq", "www");
    //订阅者【观察者，打印】
    flux2.subscribe(System.out::println);

    System.out.println("------");

    //发布者【被观察者】
    Flux<String> flux3 = Flux.fromIterable(Arrays.asList("eee", "rrrr"));
    //订阅者【观察者，打印】
//    flux3.subscribe(System.out::println);

    System.out.println("------");
    Stream<String> stream = Stream.of("from", "stream");
    Flux<String> flux4 = Flux.fromStream(stream);
//    flux4.subscribe(System.out::println);

    System.out.println("------");
    Flux<Integer> flux5 = Flux.range(2, 5);
    flux5.subscribe(System.out::println);

    System.out.println("------");
    Flux<String> mergeFlux = flux4.mergeWith(flux3);
    mergeFlux.subscribe(System.out::println);


    System.out.println("------");
    //动态创建【同步】
    Flux.generate(sink -> {
      //注意：这里的next只能调一次
      sink.next("next123");
      sink.complete();
    }).subscribe(System.out::println);


  }
}

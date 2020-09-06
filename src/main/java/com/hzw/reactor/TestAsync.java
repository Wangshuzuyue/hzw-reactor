package com.hzw.reactor;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 异步使用的场景
 */
public class TestAsync {
  public static void main(String[] args) throws InterruptedException {

    System.out.println("--------1111111111111111111");
    //动态创建【异步】
    Flux.create(sink -> {
      //这里的next可以调多次
      sink.next("next123");
      sink.next("next124");
      sink.next("next125");
      sink.complete();
    }).subscribe(str ->{
      System.out.println(Thread.currentThread().getName());
      System.out.println(str);
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    System.out.println("--------22222222222222222222");
  }
}

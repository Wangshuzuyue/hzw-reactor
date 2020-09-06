package com.hzw.reactor.controller;

import com.hzw.reactor.entity.Person;
import com.hzw.reactor.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RestController
public class TestController {

  @Autowired
  private TestService testService;


  @GetMapping("/testMono1")
  public String testMono1(){
    System.out.println("---1-----1111111111111111111");
    String result = getResult();
    System.out.println("---1-----22222222222222222222");
    return result;
  }

  @GetMapping("/testMono2")
  public Mono<String> testMono2(){
    System.out.println("----2----1111111111111111111");
    Mono<String> mono = Mono.create(sink -> getResult());
    System.out.println("----2----22222222222222222222");
    return mono;
  }

  @GetMapping("/testMono3")
  public Mono<Object> testMono3(){
    System.out.println("----3----1111111111111111111");
    Mono<Object> mono = Mono.create(monoSink -> {
      //3.封装数据
      monoSink.success(testService.getPerson());
    }).doOnSubscribe( subscription -> {
      //subscription，订阅者，是MonoSink
      //1.最先执行【订阅数据】
      System.out.println("doOnSubscribe:" + subscription);
    }).doOnNext(data -> {
      //2.得到数据
      System.out.println("doOnNext:" + data);
    }).doOnSuccess(data -> {
      //4.整体完成
      System.out.println("doOnSuccess:" + data);
    });
    System.out.println("----3----22222222222222222222");
    return mono;
  }


  @GetMapping("/testFlux1")
  public Flux<Person> testFlux1(){
    System.out.println("----flux3----1111111111111111111");
    Flux<Person> flux = Flux.create(fluxSink -> fluxSink.next(testService.getPerson()));
    System.out.println("----flux3----22222222222222222222");
    return flux;
  }


  private String getResult(){
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "mono123212321";
  }

}

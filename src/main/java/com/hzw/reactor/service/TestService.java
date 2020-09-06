package com.hzw.reactor.service;

import com.hzw.reactor.entity.Person;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

  public Person getPerson(){
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  Person person = new Person();
  person.setId(12321L);
  person.setName("黄祖望");
  return person;
  }

  public Mono<Person> getPersonMono(){
    return null;
  }

  public Flux<Person> getPersonFlux(){
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    List<Person> list = new ArrayList<>();
    Person person1 = new Person();
    person1.setId(123211L);
    person1.setName("黄祖望1");
    Person person2 = new Person();
    person2.setId(123212L);
    person2.setName("黄祖望2");
    list.add(person1);
    list.add(person2);
    Flux<Person> flux = Flux.fromIterable(list);
    return flux;
  }
}

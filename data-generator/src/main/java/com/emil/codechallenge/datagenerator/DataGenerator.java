package com.emil.codechallenge.datagenerator;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DataGenerator {

  public static void main(String[] args) {
    SpringApplication.run(DataGenerator.class, args);
    Random random = new Random(100);
    System.out.println((random.nextFloat()*100)/100);
  }
}

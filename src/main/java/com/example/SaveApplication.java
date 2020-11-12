package com.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@SpringBootApplication
public class SaveApplication {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(SaveApplication.class, args);
  }
}

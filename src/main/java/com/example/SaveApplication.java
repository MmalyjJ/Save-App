package com.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;


@Controller
@SpringBootApplication
@EnableJpaRepositories
public class SaveApplication extends SpringBootServletInitializer {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(SaveApplication.class, args);
  }
}

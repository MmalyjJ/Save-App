package com.example.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/documentation")
@AllArgsConstructor
public class DocumentationController {
    @RequestMapping("/")
    public String doc(){
        return "documentation";
    }

    @RequestMapping("")
    public String doc0(){
        return "documentation";
    }

    @RequestMapping("/status")
    public String status(){
        return "status";
    }

    @RequestMapping("/user")
    public String user(){
        return "user";
    }

    @RequestMapping("/news")
    public String news(){
        return "news";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/wallet")
    public String wallet(){
        return "wallet";
    }

}

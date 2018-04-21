package com.yq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class HelloWorldController {

    @GetMapping(value = "/hello", produces = "application/json;charset=UTF-8")
    public String hello() {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss z");
        String now = format.format(date);
        return "Query Hello World." + now;
    }
}
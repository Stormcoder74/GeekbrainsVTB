package com.geekbrains.teryaevs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @GetMapping("index")
    public String doSomething(){
        return "index";
    }
}

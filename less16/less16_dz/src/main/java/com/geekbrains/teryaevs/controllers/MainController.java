package com.geekbrains.teryaevs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("index")
    public String doSomething(){
        return "index";
    }

    @GetMapping("hello/{name}")
    public String doSomething2(@PathVariable("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/form")
    public String showForm() {
        return "simple-form";
    }

    @PostMapping("/form")
    public String saveForm(@RequestParam(value = "name") String name,
                           @RequestParam(value = "email") String email) {
        System.out.println(name);
        System.out.println(email);
        return "redirect:/index";
    }
}

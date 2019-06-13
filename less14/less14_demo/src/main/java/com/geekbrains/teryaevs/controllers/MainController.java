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

    @GetMapping("/addcat")
    public String showAddCatForm(Model model) {
        Cat cat = new Cat(1L, null, null);
        model.addAttribute("cat", cat);
        return "cat-form";
    }

    @PostMapping("/addcat")
    public String showAddCatForm(@ModelAttribute("cat") Cat cat) {
        System.out.println(cat.getId() + " " + cat.getName() + " " + cat.getColor());
        return "redirect:/index";
    }
}

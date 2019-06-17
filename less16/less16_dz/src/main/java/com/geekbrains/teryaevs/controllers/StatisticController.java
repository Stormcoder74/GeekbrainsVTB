package com.geekbrains.teryaevs.controllers;

import com.geekbrains.teryaevs.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistic")
public class StatisticController {
    private StatisticService statisticService;

    @Autowired
    public void setStatisticService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("products")
    public String productStatistic(Model model){
        model.addAttribute("statistic", statisticService.getStatistic());
        return "products-statistic";
    }
}

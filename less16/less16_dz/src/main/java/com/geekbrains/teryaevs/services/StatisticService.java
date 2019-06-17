package com.geekbrains.teryaevs.services;

import com.geekbrains.teryaevs.entities.ProductStatistic;
import com.geekbrains.teryaevs.repositories.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {
    private StatisticRepository statisticRepository;

    @Autowired
    public void setStatisticRepository(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public void update(Long id){
        ProductStatistic statistic = statisticRepository.findById(id).orElse(null);
        if(statistic == null){
            statistic = new ProductStatistic(id, 1);
        }else{
            statistic.setViews(statistic.getViews() + 1);
        }
        statisticRepository.save(statistic);
    }

    public List<ProductStatistic> getStatistic(){
        return statisticRepository.findTop3ByOrderByViewsDesc();
    }
}

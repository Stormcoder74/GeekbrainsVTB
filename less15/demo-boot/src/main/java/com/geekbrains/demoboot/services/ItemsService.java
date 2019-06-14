package com.geekbrains.demoboot.services;

import com.geekbrains.demoboot.entities.Item;
import com.geekbrains.demoboot.entities.ItemProjection;
import com.geekbrains.demoboot.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService {
    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Page<Item> getItemsWithPagingAndFiltering(Specification<Item> specifications, Pageable pageable) {
        return itemRepository.findAll(specifications, pageable);
    }
}

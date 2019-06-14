package com.geekbrains.demoboot.repositories.specifications;


import com.geekbrains.demoboot.entities.Item;
import org.springframework.data.jpa.domain.Specification;

public class ItemsSpecs {
    public static Specification<Item> titleContains(String word) {
        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), "%" + word + "%");
    }

    public static Specification<Item> costGreaterThanOrEq(int value) {
        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), value);
    }
}

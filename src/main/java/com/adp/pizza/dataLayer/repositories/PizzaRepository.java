package com.adp.pizza.dataLayer.repositories;

import com.adp.pizza.dataLayer.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}

package com.adp.pizza.dataLayer.repositories;

import com.adp.pizza.dataLayer.models.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Long> {
}

package com.adp.pizza.businessLayer;

import com.adp.pizza.dataLayer.dto.PizzaDTO;
import com.adp.pizza.dataLayer.models.Pizza;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BusinessHelper {

    public PizzaDTO fromPizzaToPizzaDTO(Pizza pizza) {
        PizzaDTO pizzaDTO = new PizzaDTO();
        pizzaDTO.setId(pizza.getId());
        pizzaDTO.setName(pizza.getName());
        pizzaDTO.setFavorite(pizza.isFavorite());
        pizzaDTO.setToppings(new ArrayList<>(pizza.getToppings()));
        pizzaDTO.setPhotoName(pizza.getPhotoName());
        return pizzaDTO;
    }

}

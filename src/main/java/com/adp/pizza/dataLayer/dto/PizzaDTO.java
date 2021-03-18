package com.adp.pizza.dataLayer.dto;

import com.adp.pizza.dataLayer.models.Topping;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PizzaDTO {

    private Long id;
    private String name;
    private String photoName;
    private List<Topping> toppings;

}

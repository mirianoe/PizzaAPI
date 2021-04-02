package com.adp.pizza.dataLayer.dto;

import com.adp.pizza.dataLayer.models.Topping;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PizzaDTO {

    private Long id;
    private String name;
    private String photoName;
    private boolean favorite;
    private List<Topping> toppings;

}

package com.adp.pizza.dataLayer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pizzas")
public class Pizza implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizza_id")
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 64)
    private String photoName;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "pizzas_toppings",
            joinColumns = @JoinColumn(
                    name = "pizza_id", referencedColumnName = "pizza_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "topping_id", referencedColumnName = "topping_id"))
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private Set<Topping> toppings = new HashSet<>(0);

    private boolean favorite;

    public void addToppings(List<Topping> toppings) {
        toppings.forEach(this::addTopping);
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
        topping.getPizzas().add(this);
    }

    public void removeAllToppings() {
        toppings.clear();
    }

}

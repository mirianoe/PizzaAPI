package com.adp.pizza;

import com.adp.pizza.dataLayer.models.Pizza;
import com.adp.pizza.dataLayer.models.Topping;
import com.adp.pizza.dataLayer.repositories.PizzaRepository;
import com.adp.pizza.dataLayer.repositories.ToppingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest(classes = PizzaApplication.class)
class PizzaApplicationTests {

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    ToppingRepository toppingRepository;

    @Test
    void populateDB() {

        Topping pomodoro = Topping.builder().name("Pomodoro").build();
        Topping mozzarella = Topping.builder().name("Mozzarella").build();
        Topping basilico = Topping.builder().name("Basilico").build();
        Topping funghi = Topping.builder().name("Funghi").build();
        Topping carciofini = Topping.builder().name("Carciofini").build();
        Topping prosciutto = Topping.builder().name("Prosciutto").build();
        Topping prosciuttoCotto = Topping.builder().name("Prosciutto cotto").build();
        Topping uovo = Topping.builder().name("Uovo").build();
        Topping wurstel = Topping.builder().name("Wurstel").build();
        Topping capperi = Topping.builder().name("Capperi").build();
        Topping olive = Topping.builder().name("Olive").build();
        Topping mais = Topping.builder().name("Mais").build();
        Topping tonno = Topping.builder().name("Tonno").build();
        Topping cipolla = Topping.builder().name("Cipolla").build();
        Topping zucchine = Topping.builder().name("Zucchine").build();
        Topping melanzane = Topping.builder().name("Melanzane").build();
        Topping peperoni = Topping.builder().name("Peperoni").build();

        toppingRepository.saveAll(
                List.of(
                        pomodoro,
                        mozzarella,
                        basilico,
                        funghi,
                        carciofini,
                        prosciutto,
                        prosciuttoCotto,
                        uovo,
                        wurstel,
                        capperi,
                        olive,
                        mais,
                        tonno,
                        cipolla,
                        zucchine,
                        melanzane,
                        peperoni
                ));

        Pizza margherita = Pizza
                .builder()
                .name("Margherita")
                .photoName("margherita.jpg")
                .toppings(Set.of(pomodoro, mozzarella, basilico))
                .build();

        Pizza capricciosa = Pizza
                .builder()
                .name("Capricciosa")
                .photoName("capricciosa.jpg")
                .toppings(Set.of(pomodoro, mozzarella, funghi, carciofini, prosciuttoCotto, olive))
                .build();

        /*
         * Vegetariani e Vegani accontentati!!!!
         */
        Pizza ortolana = Pizza
                .builder()
                .name("Ortolana")
                .photoName("ortolana.jpg")
                .toppings(Set.of(pomodoro, zucchine, melanzane, peperoni))
                .build();

        pizzaRepository.saveAll(
                List.of(
                        margherita,
                        capricciosa,
                        ortolana
                )
        );

    }

}

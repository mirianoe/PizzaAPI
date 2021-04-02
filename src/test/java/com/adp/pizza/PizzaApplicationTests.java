package com.adp.pizza;

import com.adp.pizza.businessLayer.BusinessService;
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

    @Autowired
    BusinessService businessService;

    Topping fiorDiLatte = Topping.builder().name("Fior di Latte").build();
    Topping funghiPorciniDellaSila = Topping.builder().name("Funghi porcini della Sila").build();
    Topping provoloneDelMonaco = Topping.builder().name("Provolone del Monaco").build();
    Topping salsaDiPeperoniGialli = Topping.builder().name("Salsa di Peperoni gialli").build();
    Topping salsicciaDiFegatoSbriciolata = Topping.builder().name("Salsiccia di Fegato sbriciolata").build();
    Topping patateSilane = Topping.builder().name("Patate silane").build();
    Topping lardoDiMaialeNeroSilano = Topping.builder().name("Lardo di maiale nero silano").build();
    Topping riduzionePeperoniGialliEPrezzemolo = Topping.builder().name("Riduzione di Peperoni gialli e prezzemolo").build();
    Topping rucola = Topping.builder().name("Rucola").build();
    Topping ricottaDiPecoraAlloZafferano = Topping.builder().name("Ricotta di pecora allo zafferano").build();
    Topping pomodoriSecchi = Topping.builder().name("Pomodori secchi").build();
    Topping gherigliDiNoce = Topping.builder().name("Gherigli di noce").build();
    Topping pomodoriniRossi = Topping.builder().name("Pomodorini rossi").build();
    Topping speckMaialeNeroCalabrese = Topping.builder().name("Speck maiale nero calabrese").build();
    Topping granellaDiPistacchio = Topping.builder().name("Granella di pistacchio").build();
    Topping battutoDiPistacchioDiBronte = Topping.builder().name("Battuto di pistacchio di Bronte").build();
    Topping figliata = Topping.builder().name("Figliata").build();
    Topping mortadella = Topping.builder().name("Mortadella").build();
    Topping cremaDiGorgonzolaEPistacchio = Topping.builder().name("Crema di gorgonzola e pistacchio").build();
    Topping pacchetelleGialle = Topping.builder().name("Pacchetelle gialle").build();
    Topping bottargaDiMuggine = Topping.builder().name("Bottarga di muggine").build();
    Topping aliciDiCetara = Topping.builder().name("Alici di Cetara").build();
    Topping zesteDiLimoneDiCetraro = Topping.builder().name("Zeste di Limone di Cetraro").build();
    Topping stracciatella = Topping.builder().name("Stracciatella").build();
    Topping basilico = Topping.builder().name("Basilico").build();

    @Test
    void populateDB() {

        Pizza pizzaDAsila = Pizza
                .builder()
                .name("Pizzad'Asila")
                .photoName("pizzadasila.jpg")
                .toppings(Set.of(fiorDiLatte, patateSilane, funghiPorciniDellaSila, lardoDiMaialeNeroSilano))
                .build();

        Pizza pizzaSacroEProfano = Pizza
                .builder()
                .name("Sacro e profano")
                .photoName("sacroeprofano.jpg")
                .toppings(Set.of(fiorDiLatte, salsaDiPeperoniGialli , salsicciaDiFegatoSbriciolata, funghiPorciniDellaSila, riduzionePeperoniGialliEPrezzemolo, provoloneDelMonaco))
                .build();

        Pizza pizzaDAccarezzare = Pizza
                .builder()
                .name("Pizzad'Accarezzare")
                .photoName("pizzadaccarezzare.jpg")
                .toppings(Set.of(fiorDiLatte, rucola, ricottaDiPecoraAlloZafferano, pomodoriSecchi, gherigliDiNoce))
                .build();

        Pizza pizzaDAlessandro = Pizza
                .builder()
                .name("Pizzad'Alessandro")
                .photoName("pizzadalessandro.jpg")
                .toppings(Set.of(fiorDiLatte, pomodoriniRossi, speckMaialeNeroCalabrese, granellaDiPistacchio, battutoDiPistacchioDiBronte))
                .build();

        Pizza pizzaEFigliata = Pizza
                .builder()
                .name("Pizza e Figliata")
                .photoName("pizzaefigliata.jpg")
                .toppings(Set.of(figliata, mortadella, cremaDiGorgonzolaEPistacchio))
                .build();

        Pizza pizzaBufalaDiMare = Pizza
                .builder()
                .name("Pizza bufala di mare")
                .photoName("pizzabufaladimare.jpg")
                .toppings(Set.of(fiorDiLatte, pacchetelleGialle , bottargaDiMuggine, aliciDiCetara, zesteDiLimoneDiCetraro, stracciatella, basilico))
                .build();

        pizzaRepository.saveAll(
                List.of(
                        pizzaDAsila,
                        pizzaSacroEProfano,
                        pizzaDAccarezzare,
                        pizzaDAlessandro,
                        pizzaEFigliata,
                        pizzaBufalaDiMare
                )
        );

    }

}

package com.adp.pizza.presentationLayer;

import com.adp.pizza.businessLayer.BusinessException;
import com.adp.pizza.businessLayer.BusinessService;
import com.adp.pizza.dataLayer.dto.PizzaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
@CrossOrigin
public class RESTPizzaController {

    private final BusinessService businessService;

    public RESTPizzaController(
            BusinessService businessService
    ) {
        this.businessService = businessService;
    }

    @GetMapping()
    public ResponseEntity<List<PizzaDTO>> getAllPizza() {
        List<PizzaDTO> found = businessService.getAllPizzas();
        if (found.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<PizzaDTO> getPizzaById(
            @PathVariable(value = "id") Long id
    ) {
        PizzaDTO found = businessService.getPizzaById(id);
        if (found == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PizzaDTO>> findPizzaByName(
            @RequestParam(value = "pizzaName") String pizzaName
    ) {
        List<PizzaDTO> found = businessService.findPizzaByName(pizzaName);
        if (found.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<PizzaDTO>> getFavoritePizzas() {
        List<PizzaDTO> found = businessService.getFavoritePizzas();
        if (found.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PizzaDTO> addPizza(
            @RequestParam("pizza") String pizza,
            @RequestParam("photo") MultipartFile photo
    ) {
        try {
            PizzaDTO pizzaDTO = new ObjectMapper().readValue(pizza, PizzaDTO.class);
            PizzaDTO saved = businessService.addPizza(pizzaDTO, photo);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (JsonProcessingException | BusinessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping()
    public ResponseEntity<PizzaDTO> updatePizza(
            @RequestBody PizzaDTO pizzaDTO
    ) {
        PizzaDTO saved = businessService.updatePizza(pizzaDTO);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @PutMapping("/toggle")
    public ResponseEntity<PizzaDTO> toggleFavoritePizza(
            @RequestBody Long id
    ) {
        PizzaDTO saved = businessService.toggleFavoritePizza(id);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<?> deletePizza(
            @PathVariable(value = "id") Long id
    ) {
        businessService.deletePizza(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

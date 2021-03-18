package com.adp.pizza.presentationLayer;

import com.adp.pizza.businessLayer.BusinessService;
import com.adp.pizza.dataLayer.dto.PizzaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
@CrossOrigin
public class RESTPizzaController {

    private final BusinessService businessService;

    public RESTPizzaController(BusinessService businessService) {
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
    public ResponseEntity<PizzaDTO> getPizzaById(@PathVariable(value = "id") Long id) {
        PizzaDTO found = businessService.getPizzaById(id);
        if (found == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

}

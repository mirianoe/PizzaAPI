package com.adp.pizza.presentationLayer;

import com.adp.pizza.businessLayer.BusinessService;
import com.adp.pizza.dataLayer.models.Topping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/toppings")
@CrossOrigin
public class RESTToppingController {

    private final BusinessService businessService;

    public RESTToppingController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @GetMapping()
    public ResponseEntity<List<Topping>> getAllToppings() {
        List<Topping> found = businessService.getAllTopping();
        if (found.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

}

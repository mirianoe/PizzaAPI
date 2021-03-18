package com.adp.pizza.businessLayer;

public class BusinessNotFoundException extends BusinessException {

    public BusinessNotFoundException(Long id) {
        super("Could not find entity with " + id);
    }

}

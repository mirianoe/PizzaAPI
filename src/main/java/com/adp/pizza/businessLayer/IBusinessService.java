package com.adp.pizza.businessLayer;

import com.adp.pizza.dataLayer.dto.PizzaDTO;

import java.util.List;

public interface IBusinessService {

    List<PizzaDTO> getAllPizzas() throws BusinessException;

    PizzaDTO getPizzaById(Long id) throws BusinessNotFoundException;

}

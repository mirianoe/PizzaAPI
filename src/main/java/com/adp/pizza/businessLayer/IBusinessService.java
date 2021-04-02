package com.adp.pizza.businessLayer;

import com.adp.pizza.dataLayer.dto.PizzaDTO;
import com.adp.pizza.dataLayer.models.Topping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBusinessService {

    List<PizzaDTO> getAllPizzas() throws BusinessException;

    PizzaDTO getPizzaById(Long id) throws BusinessNotFoundException;

    List<PizzaDTO> getFavoritePizzas() throws BusinessException;

    List<PizzaDTO> findPizzaByName(String pizzaName) throws BusinessException;

    PizzaDTO addPizza(PizzaDTO pizzaDTO, MultipartFile photo) throws BusinessException;

    PizzaDTO toggleFavoritePizza(Long id) throws BusinessException;

    PizzaDTO updatePizza(PizzaDTO pizzaDTO) throws BusinessException;

    void deletePizza(Long id) throws BusinessException;

    List<Topping> getAllTopping() throws BusinessException;

}

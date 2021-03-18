package com.adp.pizza.businessLayer;

import com.adp.pizza.dataLayer.dto.PizzaDTO;
import com.adp.pizza.dataLayer.models.Pizza;
import com.adp.pizza.dataLayer.repositories.PizzaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BusinessService implements IBusinessService {

    private final BusinessHelper businessHelper;

    private final PizzaRepository pizzaRepository;

    public BusinessService(
            BusinessHelper businessHelper,
            PizzaRepository pizzaRepository
    ) {
        this.businessHelper = businessHelper;
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PizzaDTO> getAllPizzas() throws BusinessException {
        return pizzaRepository
                .findAll()
                .stream()
                .map(businessHelper::fromPizzaToPizzaDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PizzaDTO getPizzaById(Long id) throws BusinessNotFoundException {
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(() -> new BusinessNotFoundException(id));
        return businessHelper.fromPizzaToPizzaDTO(pizza);
    }

}

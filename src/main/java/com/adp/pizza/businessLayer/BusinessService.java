package com.adp.pizza.businessLayer;

import com.adp.pizza.dataLayer.dto.PizzaDTO;
import com.adp.pizza.dataLayer.models.Pizza;
import com.adp.pizza.dataLayer.models.Topping;
import com.adp.pizza.dataLayer.repositories.PizzaRepository;
import com.adp.pizza.dataLayer.repositories.ToppingRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BusinessService implements IBusinessService {

    private final BusinessHelper businessHelper;

    private final PizzaRepository pizzaRepository;

    private final ToppingRepository toppingRepository;

    @Value("${pizza-photo-upload-path}")
    private String pizzaPhotoUploadPath;

    public BusinessService(
            BusinessHelper businessHelper,
            PizzaRepository pizzaRepository,
            ToppingRepository toppingRepository
    ) {
        this.businessHelper = businessHelper;
        this.pizzaRepository = pizzaRepository;
        this.toppingRepository = toppingRepository;
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

    @Override
    public List<PizzaDTO> getFavoritePizzas() throws BusinessException {
        return pizzaRepository
                .findByFavoriteIsTrue()
                .stream()
                .map(businessHelper::fromPizzaToPizzaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PizzaDTO> findPizzaByName(String pizzaName) throws BusinessException {
        return this.pizzaRepository
                .findByNameContains(pizzaName)
                .stream()
                .map(this.businessHelper::fromPizzaToPizzaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PizzaDTO addPizza(PizzaDTO pizzaDTO, MultipartFile photo) throws BusinessException {
        try {
            Pizza pizza = new Pizza();
            pizza.setName(pizzaDTO.getName());
            pizza.setPhotoName(pizzaDTO.getPhotoName());
            pizza.addToppings(pizzaDTO
                    .getToppings()
                    .stream()
                    .map(topping -> toppingRepository.getOne(topping.getId()))
                    .collect(Collectors.toList())
            );
            Pizza added = pizzaRepository.save(pizza);
            Path pizzaPhotoUploadDirectory = Paths.get(this.pizzaPhotoUploadPath);
            Path downloadedFile = pizzaPhotoUploadDirectory
                    .resolve(Paths.get(photo.getOriginalFilename()));
            Files.deleteIfExists(downloadedFile);
            Files.copy(photo.getInputStream(), downloadedFile);
            return businessHelper.fromPizzaToPizzaDTO(added);
        } catch (IOException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public PizzaDTO toggleFavoritePizza(Long id) throws BusinessException {
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(() -> new BusinessNotFoundException(id));
        pizza.setFavorite(!pizza.isFavorite());
        return businessHelper.fromPizzaToPizzaDTO(pizzaRepository.save(pizza));
    }

    @Override
    public PizzaDTO updatePizza(PizzaDTO pizzaDTO) throws BusinessException {
        Pizza pizza = pizzaRepository.getOne(pizzaDTO.getId());
        pizza.removeAllToppings();
        pizza.addToppings(pizzaDTO
                .getToppings()
                .stream()
                .map(topping -> toppingRepository.getOne(topping.getId()))
                .collect(Collectors.toList())
        );
        return businessHelper.fromPizzaToPizzaDTO(pizzaRepository.save(pizza));
    }

    @Override
    public void deletePizza(Long id) throws BusinessException {
        try {
            Pizza pizza = pizzaRepository.getOne(id);
            Path pizzaPhotoUploadDirectory = Paths.get(this.pizzaPhotoUploadPath);
            Path downloadedFile = pizzaPhotoUploadDirectory
                    .resolve(Paths.get(pizza.getPhotoName()));
            Files.deleteIfExists(downloadedFile);
            pizzaRepository.deleteById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Topping> getAllTopping() throws BusinessException {
        return toppingRepository.findAllByOrderByNameAsc();
    }

}

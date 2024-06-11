package ru.buzynnikov.bidservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.buzynnikov.bidservice.dto.ProductDTO;
import ru.buzynnikov.bidservice.exceptions.ProductNotFoundException;
import ru.buzynnikov.bidservice.models.Product;
import ru.buzynnikov.bidservice.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

//Сервис для обработки продукта
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    //Возврат списка всех продуктов
    public Iterable<Product> getAllProduct(){
        return productRepository.findAll();
    }
    //Добавление нового продукта
    public Product saveProduct(ProductDTO product){
        return productRepository.save(createProduct(product));
    }
    //Обновление продукта
    public Product updateProduct(Long id, ProductDTO dto){
        Optional<Product> temp = productRepository.findById(id);
        if (temp.isPresent()){
            Product product = temp.get();
            product.setName(dto.getName());
            product.setPrice(BigDecimal.valueOf(dto.getPrice()));
            return productRepository.save(product);
        }
        throw new ProductNotFoundException("Продукт с id " + id + " не найден");
    }
    //Удаление продукта
    public Long deleteProduct(Long id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return id;
        } else {
            throw new ProductNotFoundException("Продукт с id " + id + " не найден");
        }
    }
    //Создание объекта продукта
    private Product createProduct(ProductDTO dto){
        Product product = new Product();
        product.setName(dto.getName());
        product.setWeight(BigDecimal.valueOf(dto.getWeight()));
        product.setPrice(BigDecimal.valueOf(dto.getPrice()));
        return product;
    }
    //Поиск продукта в базе данных по ID
    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow();
    }
}

package ru.buzynnikov.bidservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.buzynnikov.bidservice.dto.ProductDTO;
import ru.buzynnikov.bidservice.exceptions.ProductNotFoundException;
import ru.buzynnikov.bidservice.models.Product;
import ru.buzynnikov.bidservice.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Iterable<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public Product saveProduct(ProductDTO product){
        return productRepository.save(createProduct(product));
    }
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
    public void deleteProduct(Long id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException("Продукт с id " + id + " не найден");
        }
    }

    private Product createProduct(ProductDTO dto){
        Product product = new Product();
        product.setName(dto.getName());
        product.setWeight(BigDecimal.valueOf(dto.getWeight()));
        product.setPrice(BigDecimal.valueOf(dto.getPrice()));
        return product;
    }
    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow();
    }
}

package ru.buzynnikov.bidservice;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.buzynnikov.bidservice.controllers.rest.ProductController;
import ru.buzynnikov.bidservice.dto.ProductDTO;
import ru.buzynnikov.bidservice.models.Product;
import ru.buzynnikov.bidservice.repositories.ProductRepository;
import ru.buzynnikov.bidservice.services.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductIntegrationTest {
    @Autowired
    ProductController controller;
    @Autowired
    ProductService service;
    @Autowired
    ProductRepository repository;

    private static Product product_1;
    private static ProductDTO dto_1;
    private static ProductDTO dto_2;

    @BeforeAll
    public static void setProducts(){
        product_1 = new Product();
        Product product_2 = new Product();
        product_1.setName("Бекон");
        product_1.setWeight(BigDecimal.valueOf(1));
        product_1.setPrice(BigDecimal.valueOf(650));
        product_2.setName("Сыр");
        product_2.setWeight(BigDecimal.valueOf(10));
        product_2.setPrice(BigDecimal.valueOf(4130));

        dto_1 = new ProductDTO();
        dto_2 = new ProductDTO();

        dto_1.setPrice(560);
        dto_1.setName("Бекон");
        dto_1.setWeight(1);

        dto_2.setPrice(4130);
        dto_2.setName("Сыр");
        dto_2.setWeight(10);
    }


    @Test
    public void handleGetAllProducts(){
        Product saved_1 = this.service.saveProduct(dto_1);
        Product saved_2 = this.service.saveProduct(dto_2);
        Iterable<Product> products = List.of(saved_1,saved_2);
        ResponseEntity<Iterable<Product>> responseEntity = this.controller.getAllProduct();
        System.out.println(responseEntity.getBody());
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
//        assertEquals(products, responseEntity.getBody());
    }
    @Test
    public void saveProductTest(){
        ResponseEntity<Product> responseEntity = this.controller.saveProduct(dto_1);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        assertEquals(product_1.getName(), Objects.requireNonNull(responseEntity.getBody()).getName());
    }
    @Test
    public void updateProductTest(){
        ProductDTO dtoUpdate = new ProductDTO();
        dtoUpdate.setPrice(650);
        dtoUpdate.setName("Бекон");
        dtoUpdate.setWeight(1);
        Product saved = this.service.saveProduct(dto_1);
        ResponseEntity<Product> responseEntity = this.controller.updateProduct(dtoUpdate,saved.getId());

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(BigDecimal.valueOf(650.0), Objects.requireNonNull(responseEntity.getBody()).getPrice());
    }
    @Test
    public void deleteByIdTest(){
        Product saved = this.service.saveProduct(dto_1);
        ResponseEntity<Long> responseEntity = this.controller.deleteProduct(saved.getId());

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(saved.getId(),responseEntity.getBody());
    }
}

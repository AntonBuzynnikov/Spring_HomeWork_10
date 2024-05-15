package ru.buzynnikov.bidservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import ru.buzynnikov.bidservice.controllers.rest.ProductController;
import ru.buzynnikov.bidservice.dto.ProductDTO;
import ru.buzynnikov.bidservice.models.Product;
import ru.buzynnikov.bidservice.repositories.ProductRepository;
import ru.buzynnikov.bidservice.services.ProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ProductIntegrationTest {
    @Autowired
    ProductController controller;
    @Autowired
    ProductService service;
    @MockBean
    ProductRepository repository;
    @Test
    public void handleGetAllProducts(){
        Iterable<Product> products = List.of(new Product(),new Product(), new Product());

        doReturn(products).when(this.repository).findAll();

        ResponseEntity<Iterable<Product>> responseEntity = this.controller.getAllProduct();


        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(products, responseEntity.getBody());
    }
}

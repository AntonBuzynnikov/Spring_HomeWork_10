package ru.buzynnikov.bidservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.buzynnikov.bidservice.controllers.rest.ProductController;
import ru.buzynnikov.bidservice.models.Product;
import ru.buzynnikov.bidservice.services.ProductService;
import static org.mockito.Mockito.doReturn;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductModuleTest {
    @InjectMocks
    ProductController controller;
    @Mock
    ProductService service;
    @Test
    @DisplayName("GET /product/all возвращает HTTP ответ со статусом 200 OK и списком задач")
    public void handleGetAllProducts(){
        Iterable<Product> products = List.of(new Product(),new Product(), new Product());

        doReturn(products).when(this.service).getAllProduct();

        ResponseEntity<Iterable<Product>> responseEntity = this.controller.getAllProduct();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(products, responseEntity.getBody());
    }

}

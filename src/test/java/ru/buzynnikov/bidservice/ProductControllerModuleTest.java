package ru.buzynnikov.bidservice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.buzynnikov.bidservice.controllers.rest.ProductController;
import ru.buzynnikov.bidservice.dto.ProductDTO;
import ru.buzynnikov.bidservice.models.Product;
import ru.buzynnikov.bidservice.services.ProductService;
import static org.mockito.Mockito.doReturn;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductControllerModuleTest {
    @InjectMocks
    ProductController controller;
    @Mock
    ProductService service;

    private static ProductDTO dto;
    private static Product product;

    @BeforeAll
    public static void getProductDto(){
        dto = new ProductDTO();
        dto.setName("Бекон");
        dto.setWeight(1);
        dto.setPrice(650);
    }
    @BeforeAll
    public static void getProduct(){
        product = new Product();
        product.setId(1L);
        product.setWeight(BigDecimal.valueOf(1));
        product.setPrice(BigDecimal.valueOf(650));
        product.setName("Бекон");
    }

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
    @Test
    public void saveProductTest(){
        doReturn(product).when(this.service).saveProduct(dto);

        ResponseEntity<Product> responseEntity = this.controller.saveProduct(dto);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
    }
    @Test
    public void updateProductTest(){
        Long id = 1L;
        product.setPrice(BigDecimal.valueOf(560));
        dto.setPrice(560);
        doReturn(product).when(this.service).updateProduct(id,dto);

        ResponseEntity<Product> responseEntity = this.controller.updateProduct(dto,id);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());

    }
    @Test
    public void deleteProductTest(){
        Long id = 1L;
        doReturn(id).when(this.service).deleteProduct(id);

        ResponseEntity<Long> responseEntity = this.controller.deleteProduct(id);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(id,responseEntity.getBody());
    }
}

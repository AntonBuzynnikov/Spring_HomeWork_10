package ru.buzynnikov.bidservice.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.buzynnikov.bidservice.dto.ProductDTO;
import ru.buzynnikov.bidservice.models.Product;
import ru.buzynnikov.bidservice.services.ProductService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;
    @GetMapping("/all")
    public ResponseEntity<Iterable<Product>> getAllProduct(){
        return new ResponseEntity<>(service.getAllProduct(), HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductDTO product){
        return new ResponseEntity<>(service.saveProduct(product),HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long id){
        return new ResponseEntity<>(service.updateProduct(id,productDTO),HttpStatus.OK);
    }
}

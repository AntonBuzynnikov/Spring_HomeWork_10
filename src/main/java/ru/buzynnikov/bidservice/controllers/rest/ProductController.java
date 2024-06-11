package ru.buzynnikov.bidservice.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.buzynnikov.bidservice.aspect.ToLog;
import ru.buzynnikov.bidservice.dto.ProductDTO;
import ru.buzynnikov.bidservice.models.Product;
import ru.buzynnikov.bidservice.services.ProductService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;
    //Получение списка всех продуктов
    @ToLog
    @GetMapping("/all")
    public ResponseEntity<Iterable<Product>> getAllProduct(){
        return new ResponseEntity<>(service.getAllProduct(), HttpStatus.OK);
    }
    /*
        Сохранение нового продукта. Пример:
        {
            "name":"Бекон",
            "weight":1.0,
            "price":650
        }
     */
    @ToLog
    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductDTO product){
        return new ResponseEntity<>(service.saveProduct(product),HttpStatus.CREATED);
    }
    //Удаление продукта
    @ToLog
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteProduct(@PathVariable Long id){
        return new ResponseEntity<>(service.deleteProduct(id), HttpStatus.OK);
    }

    /*
        Обновление продукта. Пример:
        {
            "name":"Бекон",
            "weight":1.0,
            "price":560
        }
     */
    @ToLog
    @PostMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long id){
        return new ResponseEntity<>(service.updateProduct(id,productDTO),HttpStatus.OK);
    }
}

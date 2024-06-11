package ru.buzynnikov.bidservice.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.buzynnikov.bidservice.aspect.ToLog;
import ru.buzynnikov.bidservice.models.Bid;
import ru.buzynnikov.bidservice.models.Consumption;
import ru.buzynnikov.bidservice.models.Product;
import ru.buzynnikov.bidservice.services.BidService;

import java.util.HashMap;


@RestController
@RequestMapping("/bid")
@RequiredArgsConstructor
public class BidController {
    private final BidService bidService;

    //Выполнение запроса по созданию и получению заявки
    @ToLog
    @GetMapping("/send")
    public ResponseEntity<HashMap<Product,Double>> sendBid(){
        return new ResponseEntity<>(bidService.sendBid(), HttpStatus.CREATED);
    }
    //Возвращает общую информацию по заявке
    @ToLog
    @GetMapping("/{id}")
    public ResponseEntity<Bid> getBid(@PathVariable Long id){
        return new ResponseEntity<>(bidService.getBidById(id),HttpStatus.OK);
    }
}

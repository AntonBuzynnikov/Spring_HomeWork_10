package ru.buzynnikov.bidservice.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.buzynnikov.bidservice.dto.BidDTO;
import ru.buzynnikov.bidservice.models.Bid;
import ru.buzynnikov.bidservice.models.Consumption;
import ru.buzynnikov.bidservice.models.Product;
import ru.buzynnikov.bidservice.services.BidService;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/bid")
@RequiredArgsConstructor
public class BidController {
    private final BidService bidService;

    @PostMapping("/send")
    public ResponseEntity<HashMap<Product,Double>> sendBid(@RequestBody List<Consumption> consumptions){
        System.out.println(consumptions);
        return new ResponseEntity<>(bidService.sendBid(consumptions), HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<Bid> getBid(@RequestParam("id") Long id){
        return new ResponseEntity<>(bidService.getBidById(id),HttpStatus.OK);
    }
}

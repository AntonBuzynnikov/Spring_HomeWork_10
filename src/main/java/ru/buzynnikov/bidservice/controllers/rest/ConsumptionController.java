package ru.buzynnikov.bidservice.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.buzynnikov.bidservice.aspect.ToLog;
import ru.buzynnikov.bidservice.models.Consumption;
import ru.buzynnikov.bidservice.services.ConsumptionService;
import java.util.List;

@RestController
@RequestMapping("/consumption")
@RequiredArgsConstructor
public class ConsumptionController {
    private final ConsumptionService service;

    /*
    Отправляем запрос с данными для добавление расхода. Пример:
    [
        {
            "idProduct":0000,
            "consumption":10
        },
        {
            "idProduct":0001,
            "consumption":15
        }
    ]
    Параметр idProduct должен соответсвовать параметру Product.id,
    иначе данные будут проигнорированы
     */
    @ToLog
    @PostMapping("/add")
    public ResponseEntity<List<Consumption>> addAmtConsumption(@RequestBody List<Consumption> consumptionList){
        return new ResponseEntity<>(service.addAmtConsumption(consumptionList), HttpStatus.OK);
    }
}

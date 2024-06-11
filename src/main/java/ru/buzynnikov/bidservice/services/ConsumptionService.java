package ru.buzynnikov.bidservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.buzynnikov.bidservice.models.Consumption;
import ru.buzynnikov.bidservice.repositories.ConsumptionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Сервис для обработки расхода продукта за прошедший период
@Service
@RequiredArgsConstructor
public class ConsumptionService {
    private final ConsumptionRepository repository;

    //Возвращает список с расходом продуктов
    public List<Consumption> getConsumption(){
        return repository.findAll();
    }
    //Добавление расхода
    public List<Consumption> addAmtConsumption(List<Consumption> consumptions){
        List<Consumption> consAdd = new ArrayList<>();
        consumptions.forEach(cons-> consAdd.add(updateCons(cons)));
        return consAdd;
    }
    //Удаление из базы данных расхода. В дальнейшем формируется новый расход
    public void deleteAllConsumptions(){
        repository.deleteAll();
    }
    //Обновление расхода или добавление нового, если он не находится в базе данных
    private Consumption updateCons(Consumption consumption){
        Optional<Consumption> consFromDB = repository.findById(consumption.getIdProduct());
        if(consFromDB.isPresent()){
            Consumption consUpdate = consFromDB.get();
            consUpdate.setConsumption(consumption.getConsumption() + consUpdate.getConsumption());
            return repository.save(consUpdate);
        } else{
            return repository.save(consumption);
        }
    }
}

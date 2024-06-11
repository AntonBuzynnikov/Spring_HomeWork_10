package ru.buzynnikov.bidservice.dto;

import lombok.Data;

//Объект для добавления или обновления продукта
@Data
public class ProductDTO {
    private String name;
    private double weight;
    private double price;
}

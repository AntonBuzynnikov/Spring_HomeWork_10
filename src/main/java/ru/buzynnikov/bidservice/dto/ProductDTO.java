package ru.buzynnikov.bidservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private String name;
    private double weight;
    private double price;
}

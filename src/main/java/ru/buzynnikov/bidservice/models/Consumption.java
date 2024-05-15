package ru.buzynnikov.bidservice.models;

import lombok.Data;

import java.util.HashMap;
import java.util.StringJoiner;

@Data
public class Consumption {
    private Long idProduct;
    private Double consumption;

    @Override
    public String toString() {
        return new StringJoiner(", ", Consumption.class.getSimpleName() + "[", "]")
                .add("idProduct=" + idProduct)
                .add("consumption=" + consumption)
                .toString();
    }
}

package ru.buzynnikov.bidservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.StringJoiner;

@Data
@Entity
public class Consumption {
    @Id
    @Column(name = "id_product", nullable = false)
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

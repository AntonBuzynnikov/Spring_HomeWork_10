package ru.buzynnikov.bidservice.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "quantity")
public class Quantity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productId;
    private Long bidId;
    private Double quantity;
}

package ru.buzynnikov.bidservice.dto;

import lombok.Data;
import ru.buzynnikov.bidservice.models.Product;
import ru.buzynnikov.bidservice.security.models.User;

import java.util.List;

@Data
public class BidDTO {
    private List<Product> products;
    private User user;
}

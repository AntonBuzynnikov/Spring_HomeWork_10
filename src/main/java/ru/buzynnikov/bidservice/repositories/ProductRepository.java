package ru.buzynnikov.bidservice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.buzynnikov.bidservice.models.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    Optional<Product> findByName(String name);
}

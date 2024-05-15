package ru.buzynnikov.bidservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.buzynnikov.bidservice.models.Quantity;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity,Long> {
}

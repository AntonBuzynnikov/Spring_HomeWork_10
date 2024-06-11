package ru.buzynnikov.bidservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.buzynnikov.bidservice.models.Consumption;

@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption,Long> {
}
